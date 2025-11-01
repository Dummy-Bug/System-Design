
## Parking Lot System Design Problem

### Context
You are required to design an automated parking lot system that can support both **cars** and **bikes**.  
The system should efficiently allocate, release, and track parking slots, while supporting **ticketing** and **payment** functionalities.

---

### Requirements

1. The parking lot should have a **unique number assigned to each slot**, starting from the nearest slot to the entry point.  
2. The system should support **automated ticket generation** upon vehicle entry.  
3. When a vehicle arrives, the system should:
   - Record its **registration number** and **color**.  
   - Assign the **nearest available slot** based on vehicle type.  
4. Once the vehicle leaves, the slot should be **marked as available** again.  
5. A **bike** can be parked in any nearest available **bike slot**, while a **car** can only be parked in the nearest available **car slot**.  
6. The parking lot should have a **configurable number of slots** for:
   - Cars  
   - Bikes  
   - Electric vehicles (cars or bikes)  
7. On exit, users should be able to **pay via cash, card, or Fastag**.

---

### Functional Requirements

1. Given a vehicle, park it and generate a ticket.  
2. Given a vehicle, unpark it and process payment.  
3. Given a slot number, return the vehicle parked in that slot.  
4. Given a vehicle registration number, find the slot number it is parked in.  
5. Given a color, return all registration numbers of vehicles with that color.  
6. Given a color, return all slot numbers where vehicles of that color are parked.

---

### Follow-Up Questions (Interviewer may ask)

- How will you model the system using classes and relationships?  
- What data structures will you use for nearest-slot allocation?  
- How will you ensure scalability if multiple parking lots exist?  
- How would you handle payment failures or system crashes during payment?  
- Can multiple entrances or exits be supported in your design?
