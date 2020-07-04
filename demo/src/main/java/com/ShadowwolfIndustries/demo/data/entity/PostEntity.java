package com.ShadowwolfIndustries.demo.data.entity;

import javax.persistence.*;

import com.ShadowwolfIndustries.demo.projection.VoteProjection;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Set;

@Entity
@Data
public class PostEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;
  private String title;
  @Lob
  private byte[] pic;

  @ManyToOne
  private UserEntity user;

  @OneToMany
  private Set<VoteEntity> votes;

  @JsonSetter
  public void setPic(MultipartFile file) throws IOException {
    this.pic = file.getBytes();
  }

  @JsonProperty("pic")
  public String getPictureAsBase64() throws SQLException{
    return "data:image/jpeg;base64," + new String(Base64.getEncoder().encode(pic));
  }
}
