package org.tattoo1880.wechatmini.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Collation("mtest")
//@Document("newtest")
@AllArgsConstructor
@NoArgsConstructor
public class Mtest {
	
	
	@Id
	private String id;
	
	private String content;
}
