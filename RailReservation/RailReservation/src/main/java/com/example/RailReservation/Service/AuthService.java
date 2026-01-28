package com.example.RailReservation.Service;

import com.example.RailReservation.Dto.ChangePasswordRequest;
import com.example.RailReservation.Entity.Agent;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AuthService {

    ResponseEntity<?> loginUser(Map<String,String> body);
    ResponseEntity<String> registerUser(Map<String,String> body);
    Agent getProfileById(Long id);
    Agent updateProfile(Long id,Agent agent);
    String deleteById(Long id);
    String changePassword(String email, ChangePasswordRequest request);
    String logout(HttpServletRequest request);


}
