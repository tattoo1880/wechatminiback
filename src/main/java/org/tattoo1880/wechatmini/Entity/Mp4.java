package org.tattoo1880.wechatmini.Entity;


import com.google.gson.JsonArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Table("mp4")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mp4 {
	
	@Id
	private Long id;
	private String urls;
	private String title;
	
	//todo 创建时间
	private LocalDateTime createTime = LocalDateTime.now();
	//todo 更新时间
	private Long updateTime = System.currentTimeMillis();
	
}
