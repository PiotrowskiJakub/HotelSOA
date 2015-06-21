package pl.edu.agh.soa.core.service.impl;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.edu.agh.soa.core.bean.Invoice;
import pl.edu.agh.soa.core.bean.Reservation;
import pl.edu.agh.soa.core.dao.InvoiceDAO;
import pl.edu.agh.soa.core.dao.ReservationDAO;
import pl.edu.agh.soa.core.service.InvoiceService;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

@Stateless
public class InvoiceServiceImpl implements InvoiceService {

	public static final String RESULT = "Faktura.pdf";
	private Document document;
	private Font timesClassic11, timesBold11, timesBoldWhite11, timesBold16;

	@EJB
	private InvoiceDAO invoiceDAO;

	@EJB
	private ReservationDAO reservationDAO;

	@Override
	public List<Invoice> getInvoices() {
		return invoiceDAO.getInvoices();
	}

	@Override
	public List<Invoice> getUserInvoices(Long userId) {
		return invoiceDAO.getUserInvoices(userId);
	}

	@Override
	public byte[] getInvoiceFile(Long invoiceId) {
		return invoiceDAO.getInvoiceFile(invoiceId);
	}
	
	@Override
	public void generateInvoice(Long reservationID) {
		Reservation reservation = reservationDAO.getReservation(reservationID);

		init();
		try {
			createHeadlines(reservation);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// Close document
		document.close();

		File file = new File(RESULT);
		Path path = file.toPath();

		byte[] tempData = null;
		try {
			tempData = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Byte[] data = new Byte[tempData.length];
		int i = 0;
		for (byte b : tempData)
			data[i++] = Byte.valueOf(b);

		Invoice invoice = new Invoice();
		invoice.setInvoiceName("Faktura na klienta " + reservation.getAccount().getFirstName() + " " + reservation.getAccount().getLastName());
		invoice.setAccount(reservation.getAccount());
		invoice.setHotel(reservation.getRoom().getHotel());
		invoice.setInvoiceFile(data);
		
		invoiceDAO.addInvoice(invoice);
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

	}

	private void createHeadlines(Reservation reservation)
			throws DocumentException {
		Paragraph title = new Paragraph("Faktura", timesBold16);
		title.setAlignment(Paragraph.TITLE);
		title.setSpacingAfter(50);
		document.add(title);

		DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		addLine("Klient: ", reservation.getAccount().getFirstName() + " "
				+ reservation.getAccount().getLastName());
		addLine("Hotel: ", reservation.getRoom().getHotel().getName());
		addLine("Numer pokoju: ", "" + reservation.getRoom().getNumber());
		addLine("Typ pokoju: ", reservation.getRoom().getRoomType().getName());
		addLine("Początek pobytu: ", df.format(reservation.getStartDate()));
		addLine("Koniec pobytu: ", df.format(reservation.getEndDate()));
		// addLine("Należność za pobyt: ", reservation.getRoom().get);
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
}
