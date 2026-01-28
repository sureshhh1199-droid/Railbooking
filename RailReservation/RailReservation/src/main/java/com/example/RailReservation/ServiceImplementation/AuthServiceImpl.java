package com.example.RailReservation.ServiceImplementation;

import com.example.RailReservation.Dto.ChangePasswordRequest;
import com.example.RailReservation.Entity.Agent;
import com.example.RailReservation.Repository.AgentRepository;
import com.example.RailReservation.Service.AuthService;
import com.example.RailReservation.Utill.BlacklistService;
import com.example.RailReservation.Utill.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AgentRepository agentRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    private BlacklistService blacklistService;


    @Override
    public ResponseEntity<?> loginUser(@RequestBody Map<String,String> body) {
        String email=body.get("email");
        String password=body.get("password");
        var agentOptional=agentRepository.findByEmail(email);
        if (agentOptional.isEmpty()){
            return new ResponseEntity<>("not registered", HttpStatus.UNAUTHORIZED);
        }
        Agent agent=agentOptional.get();
        if(!passwordEncoder.matches(password, agent.getPassword())){
            return new ResponseEntity<>("invalid user",HttpStatus.UNAUTHORIZED);
        }
        String token=jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token",token));

    }


    @Override
    public ResponseEntity<String> registerUser(@RequestBody Map<String,String> body) {
        String email=body.get("email");
//        String password= passwordEncoder.encode(body.get("password"));
        if(agentRepository.findByEmail(email).isPresent()){
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        }
        String roleInput=body.get("role");
        Agent.Role role=roleInput != null && roleInput.equals("ADMIN") ?
                Agent.Role.ADMIN : Agent.Role.AGENT;
        agentRepository.save(Agent.builder().email(email).password(passwordEncoder.encode(body.get("password"))).username(body.get("username"))
                .role(role).phone(body.get("phone")).name(body.get("name")).build());

        return new ResponseEntity<>("Successfully created",HttpStatus.CREATED);

    }

    @Override
    public Agent getProfileById(Long id) {
        return agentRepository.findById(id).orElseThrow(()->new RuntimeException("id not found"));
    }

    @Override
    public Agent updateProfile(Long id,Agent agent) {

        Agent existingAgent=agentRepository.findById(id).orElseThrow(()->new RuntimeException("invalid id"));
        existingAgent.setUsername(agent.getUsername());
        existingAgent.setName(agent.getName());
        existingAgent.setEmail(agent.getEmail());
        existingAgent.setPhone(agent.getPhone());
        return agentRepository.save(existingAgent);
    }

    @Override
    public String deleteById(Long id) {
        agentRepository.deleteById(id);
        return "Deleted successfully";
    }

    @Override
    public String changePassword(String email, ChangePasswordRequest request) {

        Agent agent=agentRepository.findByEmail(email).orElseThrow(()->new RuntimeException("user not found"));
        if(!passwordEncoder.matches(request.getOldPassword(),agent.getPassword())){
            return "invalid old password";
        }
        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            return "newPassword and confirmNewPassword must be same";
        }
        agent.setPassword(passwordEncoder.encode(request.getNewPassword()));
        agentRepository.save(agent);
        return "password changed successfully";
    }
    @Override
    public String logout(HttpServletRequest request) {
        String authHeader=request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            String token=authHeader.substring(7);
            blacklistService.blacklistToken(token);
            SecurityContextHolder.clearContext();
            return "logout successfull";
        }
        return "no token found";
    }
}
