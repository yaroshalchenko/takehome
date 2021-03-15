package com.challenge.demo.dto;

import com.challenge.demo.domain.User;
import java.util.UUID;
import lombok.Data;

@Data
public class UserDTO {
  private Long userId;
  private UUID userUUID;
  private String username;

  public static UserDTO build(User user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setUserId(user.getUserId());
    userDTO.setUserUUID(user.getUserUUID());
    userDTO.setUsername(user.getUsername());

    return userDTO;
  }
}
