package kr.kh.app.dao;

import org.apache.ibatis.annotations.Param;

import kr.kh.app.vo.BoardVO;

public interface BoardDAO {

	void insertBoard(@Param("board")BoardVO board);

}
