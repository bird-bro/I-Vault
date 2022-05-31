package com.microworld.vault.modules.system.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

/**
 * @author bird
 * @date 2022-4-19 14:45
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfoResponse {

    @JsonIgnore
    private String password;
}
