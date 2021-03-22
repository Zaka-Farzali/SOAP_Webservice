# SOAP_Webservice

Cinema SOAP webservice

The operations of the service have the following responsibilities:
 Init:
  initializes the room with the given number of rows and columns
  number of rows: 1 <= rows <= 26
  number of columns: 1 <= columns <= 100
  all the seats are free, and every previous lock or reservation is deleted
  if the number of rows or columns is outside of the given interval, a CinemaException must be thrown
 GetAllSeats:
   returns all the seats in the room
   the rows are denoted by consecutive capital letters of the English alphabet starting from the letter ‘A’
   columns are denoted by consecutive integer numbers starting from 1
 GetSeatStatus:
   returns the status (free, locked, reserved, sold) of the given seat
   if the position of the given seat is invalid, a CinemaException must be thrown
 Lock:
   locks count number of seats in the row starting from the given seat moving forward
   if the locking cannot be performed (e.g. there are not enough remaining seats in the row or there are not enough free seats), a CinemaException must be thrown, and no seats should be locked
   the operation must return a unique identifier based on which the service can look up the locked seats
 Unlock:
   releases the lock with the given identifier
   every seat belonging to this lock must be freed
   if the identifier for the lock is invalid, a CinemaException must be thrown
   Unlock does not release reservations, it only releases locks
 Reserve:
   reserves the seats of the lock with the given identifier
   if the identifier for the lock is invalid, a CinemaException must be thrown
 Buy:
   sells the seats of the lock or reservation with the given identifier
   if the identifier is invalid, a CinemaException must be thrown
