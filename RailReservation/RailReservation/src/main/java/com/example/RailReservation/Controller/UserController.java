package com.example.RailReservation.Controller;

import com.example.RailReservation.Dto.ChangePasswordRequest;
import com.example.RailReservation.Entity.Agent;
import com.example.RailReservation.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;

    @GetMapping("/getProfile/{id}")
    public Agent getProfile(@PathVariable Long id){
        return authService.getProfileById(id);
    }

    @PutMapping("/updateProfile/{id}")
    public Agent updateProfile(@PathVariable Long id,@RequestBody Agent agent){
        return authService.updateProfile(id, agent);
    }
    @DeleteMapping("deleteProfile/{id}")
    public String deleteProfile(@PathVariable Long id){
        return authService.deleteById(id);
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        return authService.logout(request);
    }


    @PutMapping("/changePassword")
    public String changePassword(@PathVariable String email, @RequestBody @Valid ChangePasswordRequest request){
        return authService.changePassword(email,request);
    }
}
