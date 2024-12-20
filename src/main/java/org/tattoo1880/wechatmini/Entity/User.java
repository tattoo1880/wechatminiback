package org.tattoo1880.wechatmini.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private Instant createTime;

    private Instant updateTime;

    private String secToken;

}
