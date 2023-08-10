package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Color;

import org.junit.*;

import othello.*;

public class JSpotBoardTest {
	

	@Test
	public void testJSpotBoardConstructor1() {
		
		JSpotBoard board = new JSpotBoard(8,8);
		
		assertEquals(8, board.getSpotWidth());
		
	}
	
	@Test
	public void testJSpotBoardConstructor2() {
		
		JSpotBoard board = new JSpotBoard(8, 8, new Color(0.5f, 0.5f, 0.5f));
		
		assertEquals(8, board.getSpotHeight());
		
	}
	
	@Test
	public void testJSpotBoardConstructor3() {
		
		JSpotBoard board = new JSpotBoard(8, 8, Color.DARK_GRAY, Color.LIGHT_GRAY);
		Spot spot = board.getSpotAt(4, 4);
		
		assertEquals(spot, board.getSpotAt(4, 4));
		
	}
	
	@Test
	public void mockitoBoardGetSpotAt() {
		
		JSpotBoard board = mock(JSpotBoard.class);
		
		JSpot spot = new JSpot(((4+4)%2 == 0) ? new Color(0.8f, 0.8f, 0.8f) : Color.BLACK, Color.BLACK, Color.YELLOW, board, 4, 4);
		
		when(board.getSpotAt(4, 4)).thenReturn(spot);
		
		assertEquals(board.getSpotAt(4, 4), spot);
		
		verify(board).getSpotAt(4, 4);
		
		
	}
	
	@Test
	public void testBoardGetSpotAtIllegal() {
		
		JSpotBoard board = new JSpotBoard(8, 8, new Color(0.5f, 0.5f, 0.5f));
		
		assertThrows(IllegalArgumentException.class, ()->board.getSpotAt(-1, 4));
		
	}
	
	@Test
	public void testGetBoard() {
		
		JSpotBoard board = new JSpotBoard(8, 8, new Color(0.5f, 0.5f, 0.5f));
		JSpot spot = new JSpot(((4+4)%2 == 0) ? new Color(0.8f, 0.8f, 0.8f) : Color.BLACK, Color.BLACK, Color.YELLOW, board, 4, 4);
		
		assertEquals(board, spot.getBoard());
		
	}
	
	@Test
	public void testSetHighlightJSpot() {
		
		JSpotBoard board = new JSpotBoard(8, 8, new Color(0.5f, 0.5f, 0.5f));
		JSpot spot = (JSpot) board.getSpotAt(0, 0);
		spot.setHighlight(Color.YELLOW);
		
		assertEquals(Color.YELLOW, spot.getHighlight());
		
	}

	@Test
	public void testJSpotBoardConstructor1Illegal() {
		
		assertThrows(IllegalArgumentException.class, ()->new JSpotBoard(-1,8));
		
	}
	
	@Test
	public void testJSpotBoardConstructor2Illegal() {
		
		assertThrows(IllegalArgumentException.class, ()->new JSpotBoard(8, -1, new Color(0.5f, 0.5f, 0.5f)));
		
	}
	
	@Test
	public void testJSpotBoardConstructor3Illegal() {
		
		assertThrows(IllegalArgumentException.class, ()->new JSpotBoard(-1, -1, Color.DARK_GRAY, Color.LIGHT_GRAY));
		
	}
	
	@Test
	public void testToggleHighlight1() {
		
		JSpotBoard board = new JSpotBoard(8, 8, new Color(0.5f, 0.5f, 0.5f));
		JSpot spot = new JSpot(((4+4)%2 == 0) ? new Color(0.8f, 0.8f, 0.8f) : Color.BLACK, Color.BLACK, Color.YELLOW, board, 4, 4);
		
		boolean check = spot.isHighlighted();
		check = !check;
		
		spot.toggleHighlight();
		
		assertEquals(check, spot.isHighlighted());
		
	}
	
	@Test
	public void testToggleHighlight2() {
		
		JSpotBoard board = new JSpotBoard(8, 8, new Color(0.5f, 0.5f, 0.5f));
		JSpot spot = new JSpot(((4+4)%2 == 0) ? new Color(0.8f, 0.8f, 0.8f) : Color.BLACK, Color.BLACK, Color.YELLOW, board, 4, 4);
		
		spot.highlightSpot();
		
		spot.toggleHighlight();
		
		assertEquals(false, spot.isHighlighted());
		
	}
	
	@Test
	public void testToggleSpot1() {
		
		JSpotBoard board = new JSpotBoard(8, 8, new Color(0.5f, 0.5f, 0.5f));
		JSpot spot = new JSpot(((4+4)%2 == 0) ? new Color(0.8f, 0.8f, 0.8f) : Color.BLACK, Color.BLACK, Color.YELLOW, board, 4, 4);
		
		boolean check = spot.isEmpty();
		
		spot.toggleSpot();
		
		assertEquals(!check, spot.isEmpty());
		
	}
	
	@Test
	public void testToggleSpot2() {
		
		JSpotBoard board = new JSpotBoard(8, 8, new Color(0.5f, 0.5f, 0.5f));
		JSpot spot = new JSpot(((4+4)%2 == 0) ? new Color(0.8f, 0.8f, 0.8f) : Color.BLACK, Color.BLACK, Color.YELLOW, board, 4, 4);
		
		spot.setSpot();
		
		spot.toggleSpot();
		
		assertEquals(true, spot.isEmpty());
		
	}
	
	@Test
	public void testGetCoordString() {
		
		JSpotBoard board = new JSpotBoard(8, 8, new Color(0.5f, 0.5f, 0.5f));
		JSpot spot = new JSpot(((4+4)%2 == 0) ? new Color(0.8f, 0.8f, 0.8f) : Color.BLACK, Color.BLACK, Color.YELLOW, board, 4, 4);
		
		String check = "(4, 4)";

		assertEquals(check, spot.getCoordString());
		
	}
	
}
