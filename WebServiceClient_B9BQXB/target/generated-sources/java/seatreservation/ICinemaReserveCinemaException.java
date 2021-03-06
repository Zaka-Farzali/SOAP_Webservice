
package seatreservation;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.3
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "CinemaException", targetNamespace = "http://www.iit.bme.hu/soi/hw/SeatReservation")
public class ICinemaReserveCinemaException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private CinemaException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ICinemaReserveCinemaException(String message, CinemaException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ICinemaReserveCinemaException(String message, CinemaException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: seatreservation.CinemaException
     */
    public CinemaException getFaultInfo() {
        return faultInfo;
    }

}
