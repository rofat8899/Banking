package com.rofat.MySQLWorkBench.data.model;

import com.rofat.MySQLWorkBench.user.model.UserAccount;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="merchant")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="master_id")
    private int maId;

    private int id;

    private String merchantId;

    private String email;

    private String phone;

    private String name;

    private String secretKey;

    @OneToMany(targetEntity = UserAccount.class,cascade = CascadeType.ALL)
    private List<UserAccount> settlements;

}
