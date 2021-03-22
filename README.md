# SOAP_Webservice

Cinema webservice.

The interface of the service is described by the following pseudo-code:

namespace SeatReservation
{
  exception CinemaException
  {
    int ErrorCode;
    string ErrorMessage;
     }
      struct Seat
      {
        string Row;
        string Column;
      }
    enum SeatStatus
    {
      Free,
      Locked,
      Reserved,
      Sold
    }
  interface ICinema
  {
    void Init(int rows, int columns) throws CinemaException;
    Seat[] GetAllSeats()throws CinemaException;
    SeatStatus GetSeatStatus(Seat seat) throws CinemaException;
    string Lock(Seat seat, int count) throws CinemaException;
    void Unlock(string lockId) throws CinemaException;
    void Reserve(string lockId) throws CinemaException;
    void Buy(string lockId) throws CinemaException;
  }
}
The pseudo-code above is only for making things readable.
2
The operations of the service have the following responsibilities:
 Init:
  o initializes the room with the given number of rows and columns
  o number of rows: 1 <= rows <= 26
  o number of columns: 1 <= columns <= 100
  o all the seats are free, and every previous lock or reservation is deleted
  o if the number of rows or columns is outside of the given interval, a CinemaException must be thrown
 GetAllSeats:
  o returns all the seats in the room
  o the rows are denoted by consecutive capital letters of the English alphabet starting from the letter ‘A’
  o columns are denoted by consecutive integer numbers starting from 1
 GetSeatStatus:
  o returns the status (free, locked, reserved, sold) of the given seat
  o if the position of the given seat is invalid, a CinemaException must be thrown
 Lock:
  o locks count number of seats in the row starting from the given seat moving forward
  o if the locking cannot be performed (e.g. there are not enough remaining seats in the row or there are not enough free seats), a CinemaException must be thrown, and no seats should be locked
  o the operation must return a unique identifier based on which the service can look up the locked seats
 Unlock:
  o releases the lock with the given identifier
  o every seat belonging to this lock must be freed
  o if the identifier for the lock is invalid, a CinemaException must be thrown
  o Unlock does not release reservations, it only releases locks
 Reserve:
  o reserves the seats of the lock with the given identifier
  o if the identifier for the lock is invalid, a CinemaException must be thrown
 Buy:
  o sells the seats of the lock or reservation with the given identifier
  o if the identifier is invalid, a CinemaException must be thrown
