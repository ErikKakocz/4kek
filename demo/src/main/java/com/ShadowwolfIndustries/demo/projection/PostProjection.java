package com.ShadowwolfIndustries.demo.projection;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostProjection {

    private Long id;
    private String title;
    private byte[] pic;
    private String user;



    @JsonSetter
    public void setPic(MultipartFile file) throws IOException {
        this.pic = file.getBytes();
    }

    @JsonProperty("pic")
    public String getFileAsBase64() throws SQLException{
        return "data:image/jpeg;base64,"+ new String(Base64.getEncoder().encode(pic));
    }
}
