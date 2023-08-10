package test;

import othello.*;
import org.junit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.Assert.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.*;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.Assert;


public class OthelloWidgetTest {

	private OthelloWidget othello = new OthelloWidget();
	
	@BeforeEach
	public void clear() {
		othello.resetGame();
	}


	@ParameterizedTest
	@CsvFileSource(resources={"/params1.csv"})
	public void testIsLegalFirstMove(String xx, String yy) {
		
		// PROVERA SVIH VALIDNIH POTEZA ZA CRNOG IGRACA
		
		int x = Integer.parseInt(xx);
		int y = Integer.parseInt(yy);
		Spot spot = othello._board.getSpotAt(x, y);
		assertEquals(true, othello.isLegalMove(spot));
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources={"/params2.csv"})
	public void testIsLegalSecondMove(String xx, String yy) {
		
		// PROVERA SVIH VALIDNIH POTEZA ZA BELOG IGRACA
	
		int x = Integer.parseInt(xx);
		int y = Integer.parseInt(yy);
		Spot spot = othello._board.getSpotAt(5, 4);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(x, y);
		assertEquals(true, othello.isLegalMove(spot));
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources= {"/params3.csv"})
	public void testIsLegalMoveFalse(String xx, String yy) {
		
		// PROVERA SVIH NELEGALNIH POTEZA
		
		int x = Integer.parseInt(xx);
		int y = Integer.parseInt(yy);
		Spot spot = othello._board.getSpotAt(5, 4);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(x, y);
		assertEquals(false, othello.isLegalMove(spot));
		
	}
	
	@Test
	public void testBlackWin() {
		
		// PROVERA POBEDE CRNOG IGRACA
		// SPOT CLICKED NE MOZE DA SE POTPUNO POKRIJE
		
		winBlack();
		assertEquals(true, othello.checkWin());
		
	}
	
	@Test
	public void testWhiteWin() {
		
		// PROVERA POBEDE BELOG IGRACA
		
		winWhite();
		assertEquals(true, othello.checkWin());
	}
	
	private void winBlack() {
		Spot spot = othello._board.getSpotAt(4,5);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(5,3);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(4,2);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(5,5);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(6,4);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(3,5);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(4,6);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(5,4);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(2,4);
		othello.spotClicked(spot);
	}
	
	private void winWhite() {
		Spot spot = othello._board.getSpotAt(3,2);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(2,2);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(5,4);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(5,5);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(2,3);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(4,2);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(5,3);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(6,5);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(3,5);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(1,3);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(6,4);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(7,5);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(7,4);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(2,6);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(4,6);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(7,3);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(3,6);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(3,7);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(5,6);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(6,6);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(2,1);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(1,0);
		othello.spotClicked(spot);
	}
	
	@Test
	public void testSpotEnteredIsLegalMove() {
		
		Spot spot = othello._board.getSpotAt(5, 4);
		othello.spotEntered(spot);
		assertEquals(true, spot.isHighlighted());
		
	}
	
	@Test
	public void testSpotEnteredWon() {
		
		winBlack();
		Spot spot = othello._board.getSpotAt(5, 4);
		othello.spotEntered(spot);
		assertEquals(true, othello.checkWin());
		
	}
	
	@Test
	public void testSpotExitedNoWin() {
		
		Spot spot = othello._board.getSpotAt(5, 4);
		othello.spotExited(spot);
		assertEquals(false, spot.isHighlighted());
		
	}
	
	@Test
	public void testSpotExitedWon() {
		
		winBlack();
		Spot spot = othello._board.getSpotAt(5, 4);
		othello.spotExited(spot);
		assertEquals(true, othello.checkWin());
		
	}
	
	
	@Test
	@Ignore
	public void testHasLegalMoves() {
		
		// NEMOGUCE TESTIRATI JER JE PIECE==NULL, PA SE NIKAD NE IZVRSI
		
		//black
		int colour = 1;
		Spot spot = othello._board.getSpotAt(4,4);
		assertEquals(true, othello.hasLegalMoves(colour, spot));
		
	}
	
	
	
	@Test
	@Ignore
	public void testDraw() {
		
		// NEMA PROVERE SA GAME DRAWN, checkDraw() NEMA return true NAREDBU
		// DRAW SE PROVERAVA TAKO STO NEMA PREOSTALIH LEGALNIH POTEZA I WINNER==NULL
		
		Spot spot = othello._board.getSpotAt(5,4);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(3,5);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(2,2);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(3,2);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(2,3);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(5,3);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(4,2);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(5,2);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(6,3);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(4,1);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(5,1);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(6,5);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(5,0);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(4,0);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(3,1);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(6,0);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(2,5);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(7,2);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(7,4);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(6,2);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(5,5);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(6,4);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(7,6);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(5,6);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(7,5);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(4,5);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(3,6);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(2,4);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(1,3);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(2,7);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(4,6);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(2,6);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(3,7);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(4,7);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(6,7);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(7,3);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(7,1);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(3,0);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(2,1);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(0,3);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(0,2);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(0,1);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(1,2);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(0,4);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(1,4);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(2,0);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(6,1);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(1,5);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(0,5);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(0,6);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(1,1);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(1,0);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(1,6);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(5,7);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(1,7);
		othello.spotClicked(spot);
		spot = othello._board.getSpotAt(6,6);
		othello.spotClicked(spot);
		
		assertEquals(true, othello.checkDraw());
		

	}
	
	@Test
	public void testGetColorWhite() {
		
		Spot spot = othello._board.getSpotAt(3, 3);
		assertEquals(1, othello.getColor(spot));
		
	}
	
	@Test
	public void testGetColorBlack() {
		
		Spot spot = othello._board.getSpotAt(3, 4);
		assertEquals(0, othello.getColor(spot));
		
	}
	
	@Test
	public void testGetColorEmpty() {
		
		Spot spot = othello._board.getSpotAt(0, 0);
		assertEquals(-1, othello.getColor(spot));
		
	}
	
	@Test
	@Ignore
	public void testGetColorError() {
		
		Spot spot = othello._board.getSpotAt(-1, -1);
		assertEquals(-2, othello.getColor(spot));
		
	}
	
	@Test
	public void testInBoundsSpot() {
		
		Spot spot = othello._board.getSpotAt(4, 4);
		assertEquals(true, othello.inBounds(spot));
		
	}
	
	
	@Test
	public void testInBoundsCoordinates() {
		
		assertEquals(true, othello.inBounds(0,0));
		
	}
	
	@Test
	public void testGetColorC() {
		
		assertEquals(Color.BLACK, othello.getColorC(3,4));
		
	}
	
	
	@Test
	public void mockitoIsLegalMove() {
		
		// PROVERA LEGALNOG POTEZA KROZ MOCK OBJEKAT OTHELLOWIDGET KLASE
		
		OthelloWidget o = mock(OthelloWidget.class);
		
		o._board = new JSpotBoard(8, 8, new Color(0.5f, 0.5f, 0.5f));
		Spot spot = o._board.getSpotAt(4, 4);
		
		when(o.isLegalMove(spot)).thenReturn(true);
		
		assertEquals(true, o.isLegalMove(spot));
		
		verify(o).isLegalMove(spot);
		
	}
	
	
	@Test
	public void mockitoGetColor() {
		
		// PROVERA DOHVATANJA BOJE
		
		OthelloWidget o = mock(OthelloWidget.class);
		
		o._board = new JSpotBoard(8, 8, new Color(0.5f, 0.5f, 0.5f));
		Spot spot = o._board.getSpotAt(5, 4);
		when(o.getColor(spot)).thenReturn(0);
		
		assertEquals(0,o.getColor(spot));
		
		
	}
	
	
}
