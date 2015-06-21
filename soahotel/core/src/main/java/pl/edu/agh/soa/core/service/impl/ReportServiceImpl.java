package pl.edu.agh.soa.core.service.impl;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.edu.agh.soa.core.bean.Hotel;
import pl.edu.agh.soa.core.bean.Reservation;
import pl.edu.agh.soa.core.dao.HotelDAO;
import pl.edu.agh.soa.core.dao.ReservationDAO;
import pl.edu.agh.soa.core.service.ReportService;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Stateless
public class ReportServiceImpl implements ReportService {

	public static final String RESULT = "Raport_rezerwacji.pdf";
	private Document document;
	private Font timesClassic11, timesBold11, timesBoldWhite11, timesBold16;
	private Color darkBlue, lightBlue, lastUsedColor;

	@EJB
	private ReservationDAO reservationDAO;
	
	@EJB
	private HotelDAO hotelDAO;

	@Override
	public File generateHotelReservationsReport(Long hotelId) {
		List<Reservation> reservations = reservationDAO
				.getHotelReservations(hotelId);
		
		
		init();
		
		if(reservations == null || reservations.size() == 0) {
			Hotel hotel = hotelDAO.getHotelById(hotelId);
			try {
				createNoReservationsHeadlines(hotel);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			document.close();
			
			File file = new File(RESULT);
			return file;
		}
		
		try {
			createHeadlines(reservations);
			createReservationsTable(reservations);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// Close document
		document.close();
		
		File file = new File(RESULT);
		return file;
	}

	private void init() {
		document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(RESULT));

			document.open();

			// Font initialize
			BaseFont timesClassic = BaseFont.createFont(BaseFont.TIMES_ROMAN,
					BaseFont.CP1250, BaseFont.EMBEDDED);
			BaseFont timesBold = BaseFont.createFont(BaseFont.TIMES_BOLD,
					BaseFont.CP1250, BaseFont.EMBEDDED);

			timesClassic11 = new Font(timesClassic, 11);
			timesBold16 = new Font(timesBold, 16);
			timesBold11 = new Font(timesBold, 11);
			timesBoldWhite11 = new Font(timesBold, 11);
			timesBoldWhite11.setColor(Color.white);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		darkBlue = new Color(91, 155, 213);
		lightBlue = new Color(222, 234, 246);
	}
	
	private void createNoReservationsHeadlines(Hotel hotel) throws DocumentException {
		Paragraph title = new Paragraph(
				"Raport rezerwacji hotelu " + hotel.getName(), timesBold16);
		title.setAlignment(Paragraph.TITLE);
		title.setSpacingAfter(50);
		document.add(title);
		
		addLine("Opis hotelu: ", hotel.getDesc());
		Paragraph line = new Paragraph(
				"BRAK REZERWACJI", timesBold16);
		line.setAlignment(Paragraph.ALIGN_CENTER);
		line.setSpacingBefore(50);
		document.add(line);
	}
	
	private void createHeadlines(List<Reservation> reservations) throws DocumentException {
		Hotel hotel = reservations.get(0).getRoom().getHotel();
		Paragraph title = new Paragraph(
				"Raport rezerwacji hotelu " + hotel.getName(), timesBold16);
		title.setAlignment(Paragraph.TITLE);
		title.setSpacingAfter(50);
		document.add(title);
		
		addLine("Opis hotelu: ", hotel.getDesc());
		addLine("Liczba rezerwacji: ", "" + reservations.size());
		int gusestsNumber = countGuestsNumber(reservations);
		addLine("Łączna liczba gości: ", "" + gusestsNumber);
	}
	
	private int countGuestsNumber(List<Reservation> reservations) {
		int guestNumbers = 0;
		for(Reservation res : reservations) {
			guestNumbers += res.getRoom().getSize();
		}

		return guestNumbers;
	}

	private void addLine(String boldLine, String classicLine)
			throws DocumentException {
		Paragraph line = new Paragraph();
		line.setFont(timesBold11);
		line.add(boldLine);
		line.setFont(timesClassic11);
		line.add(classicLine);
		document.add(line);
	}
	
	private void createReservationsTable(List<Reservation> reservations) throws DocumentException {
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new int[] {100, 90, 90, 60, 40 });
		table.getDefaultCell().setBorderColor(darkBlue);

		// First row
		String[] firstRow = { "Klient", "Początek pobytu", "Koniec pobytu", "Typ pokoju", "Rozmiar pokoju" };
		addRows(firstRow, true, table, timesBoldWhite11);

		// Else rows
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		lastUsedColor = Color.white;
		for(Reservation res : reservations) {
			String guest = res.getAccount().getFirstName() + " " + res.getAccount().getLastName();
			String[] row = {guest, df.format(res.getStartDate()), df.format(res.getEndDate()), res.getRoom().getRoomType().getName(), "" + res.getRoom().getSize()};
			addRows(row, false, table, timesBold11);
		}

		Paragraph space = new Paragraph();
		space.setSpacingAfter(50);
		document.add(space);
		document.add(table);
	}

	private void addRows(String[] values, Boolean isHeader, PdfPTable table,
			Font font) {
		Color color;
		if (isHeader) {
			color = darkBlue;
		} else {
			if (lastUsedColor.equals(Color.white)) {
				color = lightBlue;
				lastUsedColor = lightBlue;
			} else {
				color = Color.white;
				lastUsedColor = Color.white;
			}
		}
		ArrayList<PdfPCell> cells = new ArrayList<>();
		for (String text : values) {
			cells.add(new PdfPCell(new Phrase(text, font)));
		}
		for (PdfPCell cell : cells) {
			cell.setBackgroundColor(color);
			table.addCell(cell);
		}
	}
}
