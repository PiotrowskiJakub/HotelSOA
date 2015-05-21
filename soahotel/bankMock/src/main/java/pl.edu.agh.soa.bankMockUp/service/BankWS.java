package pl.edu.agh.soa.bankMockUp.service;

import java.math.BigInteger;

import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;


@Stateless
@ApplicationPath("bank")
public class BankWS extends Application {


    private final BigInteger randomAmount = new BigInteger("777");

    @GET
    @Path("getBalance")
    @Consumes(MediaType.APPLICATION_JSON)
    public BigInteger getBalance() {
        return randomAmount;
    }

    @PUT
    @Path("creditAmount") // uznanie
    @Consumes(MediaType.APPLICATION_JSON)
    public void credit() {

    }


    @PUT
    @Path("debitAmount") // obciązenie konta
    @Consumes(MediaType.APPLICATION_JSON)
    public void debit() {
    }
}
