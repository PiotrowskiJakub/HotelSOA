package pl.edu.agh.soa.core.dao;

import java.util.List;

import javax.ejb.Local;

import pl.edu.agh.soa.core.bean.Invoice;

@Local
public interface InvoiceDAO {
	
	public List<Invoice> getInvoices();
	public List<Invoice> getUserInvoices(Long userId);
	public byte[] getInvoiceFile(Long invoiceId);
	public void addInvoice(Invoice invoice);
}
