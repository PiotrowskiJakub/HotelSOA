package pl.edu.agh.soa.core.service;

import java.util.List;

import javax.ejb.Remote;

import pl.edu.agh.soa.core.bean.Invoice;

@Remote
public interface InvoiceService {

	public List<Invoice> getInvoices();
	public List<Invoice> getUserInvoices(Long userId);
	public byte[] getInvoiceFile(Long invoiceId);
	public void generateInvoice(Long reservationID);
}
