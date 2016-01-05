package com.hotel.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hotel.common.repository.GenericRepository;
import com.hotel.model.Room;

@Stateless
public class RoomRepository extends GenericRepository<Room> {

	@PersistenceContext
	EntityManager em;

	@Override
	protected Class<Room> getPersistentClass() {
		return Room.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<Room> findAvailableRooms() {
		return getEntityManager().createQuery(
				"Select e From " + getPersistentClass().getSimpleName()
						+ " e where e.occupied <> 'S' Order by e.roomNumber")
				.getResultList();
	}

	public List<Room> findAvailableRoomsByDate(final LocalDateTime startDate, final LocalDateTime endDate) {
		final String sql = "SELECT * FROM room where id not in(" +
				"SELECT room.id FROM room  LEFT " +
				"OUTER  JOIN reservation_room ON " +
				"room.id = reservation_room.roomid " +
				"left outer join reservation on " +
				"reservation_room.reservationid = reservation.id " +
				"where ?1 between reservation.checkin_date and reservation.checkout_date - interval '1' day or " +
				"?2 between reservation.checkin_date + interval '1' day and reservation.checkout_date" +
				")";

		final Query query = em.createNativeQuery(sql, Room.class);
		final Timestamp timeStampStart = Timestamp.valueOf(startDate);
		final Timestamp timeStampEnd = Timestamp.valueOf(endDate);

		query.setParameter(1, timeStampStart);
		query.setParameter(2, timeStampEnd);

		final List<Room> rooms = query.getResultList();

		return rooms;
	}

}