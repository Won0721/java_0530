package kr.kh.app.vo;

import lombok.Data;

@Data
public class BoardVO {

	private int bo_num;
	private String bo_title;
	private String bo_me_id;

	public BoardVO(String title, String id) {
		this.bo_title = title;
		this.bo_me_id = id;
	}
}
