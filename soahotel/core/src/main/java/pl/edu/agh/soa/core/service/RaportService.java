package pl.edu.agh.soa.core.service;

import java.io.File;

import javax.ejb.Remote;

@Remote
public interface RaportService {

	public File generateHotelReservationsRaport(Long hotelId);
}
