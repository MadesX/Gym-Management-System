package Gym;

import java.util.GregorianCalendar;
import java.util.Vector;

public class CreateDatabase 
{
    public static void main(String[] args) 
    {
    	Member member1 = new Member("123336789", "Yossi", "Cohen", new GregorianCalendar(1990, 5, 14), "yossi@domain.com", "0501234567", "password", new GregorianCalendar(2023, 0, 1));
        Member member2 = new Member("123456780", "Maya", "Levi", new GregorianCalendar(1992, 3, 22), "maya@domain.com", "0502345678", "password", new GregorianCalendar(2026, 0, 1));
        Member member3 = new Member("123456781", "Eli", "Katz", new GregorianCalendar(1985, 7, 30), "eli@domain.com", "0503456789", "password", new GregorianCalendar(2026, 0, 1));
        Member member4 = new Member("111156782", "Noa", "Sharon", new GregorianCalendar(1993, 10, 12), "noa@domain.com", "0504567890", "password", new GregorianCalendar(2026, 0, 1));
        Member member5 = new Member("123456783", "Amit", "Goldberg", new GregorianCalendar(1987, 2, 5), "amit@domain.com", "0505678901", "password", new GregorianCalendar(2026, 0, 1));
        Member member6 = new Member("123156784", "Oren", "Mizrahi", new GregorianCalendar(1995, 11, 18), "oren@domain.com", "0506789012", "password", new GregorianCalendar(2026, 0, 1));
        Member member7 = new Member("127456785", "Tamar", "David", new GregorianCalendar(1991, 6, 10), "tamar@domain.com", "0507890123", "password", new GregorianCalendar(2026, 0, 1));
        Member member8 = new Member("223456786", "Shimon", "Rosen", new GregorianCalendar(1988, 4, 22), "shimon@domain.com", "0508901234", "password", new GregorianCalendar(2026, 0, 1));
        Member member9 = new Member("123456787", "Gal", "Aharon", new GregorianCalendar(1994, 1, 3), "gal@domain.com", "0509012345", "password", new GregorianCalendar(2026, 0, 1));
        Member member10 = new Member("123888788", "Roni", "Dahan", new GregorianCalendar(1992, 8, 15), "roni@domain.com", "0500123456", "password", new GregorianCalendar(2026, 0, 1));
        Member member11 = new Member("123456800", "Alon", "Segal", new GregorianCalendar(1989, 3, 12), "alon@domain.com", "0501122334", "password", new GregorianCalendar(2026, 0, 1));
        Member member12 = new Member("123456801", "Dana", "Carmel", new GregorianCalendar(1995, 7, 25), "dana@domain.com", "0505566778", "password", new GregorianCalendar(2026, 0, 1));
        Member member13 = new Member("123456802", "Itay", "BenAri", new GregorianCalendar(1993, 9, 10), "itay@domain.com", "0506677889", "password", new GregorianCalendar(2026, 0, 1));
        Member member14 = new Member("123456803", "Yael", "Tal", new GregorianCalendar(1997, 1, 15), "yael@domain.com", "0507788990", "password", new GregorianCalendar(2026, 0, 1));
        Member member15 = new Member("123456804", "Ido", "Eliav", new GregorianCalendar(1990, 11, 8), "ido@domain.com", "0508899001", "password", new GregorianCalendar(2026, 0, 1));

        
        Trainer trainer1 = new Trainer("111111189", "David", "Harel", new GregorianCalendar(1980, 1, 20), "david@trainer.com", "0501112222", "password", "Combat", true);
        Trainer trainer2 = new Trainer("125556790", "Avigail", "Peretz", new GregorianCalendar(1983, 3, 18), "avigail@trainer.com", "0502233445", "password", "Ninja", false);
        Trainer trainer3 = new Trainer("123456791", "Shai", "Barel", new GregorianCalendar(1986, 5, 25), "shai@trainer.com", "0503344556", "password", "Self-defense", true);
        Trainer trainer4 = new Trainer("113456792", "Nirit", "Levi", new GregorianCalendar(1990, 9, 10), "nirit@trainer.com", "0504455667", "password", "Muay Thai", false);
        Trainer trainer5 = new Trainer("123457793", "Rami", "Shulman", new GregorianCalendar(1982, 7, 2), "rami@trainer.com", "0505566778", "password", "Boxing", true);
        Trainer trainer6 = new Trainer("123456794", "Eran", "Shani", new GregorianCalendar(1984, 4, 5), "eran@trainer.com", "0506677888", "password", "Pilates", true);
        Trainer trainer7 = new Trainer("123456795", "Michal", "Oren", new GregorianCalendar(1988, 5, 20), "michal@trainer.com", "0507788999", "password", "Yoga", false);

        GroupTrainingSession group1 = new GroupTrainingSession("G001", "Combat Defense 101", 60, "COMBAT_TRAINING_AREA", 6, new Vector<Trainer>());
        GroupTrainingSession group2 = new GroupTrainingSession("G002", "Ninja Techniques", 75, "NINJA_TRAINING_AREA", 25, new Vector<Trainer>());
        GroupTrainingSession group3 = new GroupTrainingSession("G003", "Boxing Basics",  50, "WEIGHT_ROOM", 15, new Vector<Trainer>());
        GroupTrainingSession group4 = new GroupTrainingSession("G004", "Advanced Combat", 90, "COMBAT_TRAINING_AREA", 18, new Vector<Trainer>());
        GroupTrainingSession group5 = new GroupTrainingSession("G005", "Ninja Stealth Training",  60, "NINJA_TRAINING_AREA", 12, new Vector<Trainer>());
        GroupTrainingSession group6 = new GroupTrainingSession("G006", "Self-defense Workshop", 80, "MARTIAL_ARTS_DOJO", 30, new Vector<Trainer>());
        GroupTrainingSession group7 = new GroupTrainingSession("G007", "Muay Thai Kickboxing",  70, "YOGA_STUDIO", 22, new Vector<Trainer>());
        GroupTrainingSession group8 = new GroupTrainingSession("G008", "Advanced Ninja", 90, "NINJA_TRAINING_AREA", 20, new Vector<Trainer>());
        GroupTrainingSession group9 = new GroupTrainingSession("G009", "Combat for Beginners", 60, "COMBAT_TRAINING_AREA", 10, new Vector<Trainer>());
        GroupTrainingSession group10 = new GroupTrainingSession("G010", "Combat Mastery",  100, "COMBAT_TRAINING_AREA", 20, new Vector<Trainer>());
        GroupTrainingSession group11 = new GroupTrainingSession("G011", "Morning Yoga", 50, "YOGA_STUDIO", 12, new Vector<Trainer>());
        GroupTrainingSession group12 = new GroupTrainingSession("G012", "Pilates Core Strength", 60, "YOGA_STUDIO", 15, new Vector<Trainer>());
        GroupTrainingSession group13 = new GroupTrainingSession("G013", "Cardio Blast", 45, "CARDIO_AREA", 20, new Vector<Trainer>());
        GroupTrainingSession group14 = new GroupTrainingSession("G014", "HIIT Training", 40, "FITNESS_HALL", 18, new Vector<Trainer>());
        GroupTrainingSession group15 = new GroupTrainingSession("G015", "Stretching & Mobility", 30, "YOGA_STUDIO", 10, new Vector<Trainer>());
        

        Equipment equipment1 = new Equipment("E100001", "Boxing Gloves", true, new GregorianCalendar(2026, 0, 5), "COMBAT_TRAINING_AREA");
        Equipment equipment2 = new Equipment("E100002", "Punching Bag", true, new GregorianCalendar(2026, 1, 8), "COMBAT_TRAINING_AREA");
        Equipment equipment3 = new Equipment("E100003", "Kickboxing Pads", true, new GregorianCalendar(2026, 1, 15), "NINJA_TRAINING_AREA");
        Equipment equipment4 = new Equipment("E100004", "Nunchaku", true, new GregorianCalendar(2026, 2, 2), "MARTIAL_ARTS_DOJO");
        Equipment equipment5 = new Equipment("E100005", "Bo Staff", true, new GregorianCalendar(2026, 2, 10), "MARTIAL_ARTS_DOJO");
        Equipment equipment6 = new Equipment("E100006", "Heavy Dumbbells", true, new GregorianCalendar(2026, 2, 12), "WEIGHT_ROOM");
        Equipment equipment7 = new Equipment("E100007", "Kettlebells", true, new GregorianCalendar(2026, 2, 15), "WEIGHT_ROOM");
        Equipment equipment8 = new Equipment("E100008", "Speed Ladder", true, new GregorianCalendar(2026, 2, 18), "FITNESS_HALL");
        Equipment equipment9 = new Equipment("E100009", "Medicine Balls", true, new GregorianCalendar(2026, 2, 20), "FITNESS_HALL");
        Equipment equipment10 = new Equipment("E100010", "Jump Ropes", true, new GregorianCalendar(2026, 2, 22), "CARDIO_AREA");
        Equipment equipment11 = new Equipment("E100011", "Resistance Bands", true, new GregorianCalendar(2026, 2, 25), "CARDIO_AREA");
        Equipment equipment12 = new Equipment("E100012", "Barbell Plates", true, new GregorianCalendar(2026, 2, 28), "WEIGHT_ROOM");
        Equipment equipment13 = new Equipment("E100013", "Boxing Speed Bag", true, new GregorianCalendar(2026, 3, 1), "COMBAT_TRAINING_AREA");
        Equipment equipment14 = new Equipment("E100014", "Punching Mitts", true, new GregorianCalendar(2026, 3, 5), "COMBAT_TRAINING_AREA");
        Equipment equipment15 = new Equipment("E100015", "Foam Rollers", true, new GregorianCalendar(2026, 3, 7), "YOGA_STUDIO");
        Equipment equipment16 = new Equipment("E100016", "Stretching Bands", true, new GregorianCalendar(2026, 3, 10), "YOGA_STUDIO");
        Equipment equipment17 = new Equipment("E100017", "Slam Balls", true, new GregorianCalendar(2026, 3, 12), "FITNESS_HALL");
        Equipment equipment18 = new Equipment("E100018", "Parallel Bars", true, new GregorianCalendar(2026, 3, 14), "MARTIAL_ARTS_DOJO");
        Equipment equipment19 = new Equipment("E100019", "Power Racks", true, new GregorianCalendar(2026, 3, 15), "WEIGHT_ROOM");
        Equipment equipment20 = new Equipment("E100020", "Trampoline", true, new GregorianCalendar(2026, 3, 17), "SPINNING_ROOM");

    
        PersonalTrainingSession personal1 = new PersonalTrainingSession("P001", "One on One Combat", 60, "COMBAT_TRAINING_AREA", trainer1);
        PersonalTrainingSession personal2 = new PersonalTrainingSession("P002", "Advanced Ninja Training", 90, "NINJA_TRAINING_AREA", trainer2);
        PersonalTrainingSession personal3 = new PersonalTrainingSession("P003", "One-on-One Yoga", 50, "YOGA_STUDIO", trainer7);        
        PersonalTrainingSession personal4 = new PersonalTrainingSession("P004", "Personal Pilates", 60, "FITNESS_HALL", trainer6);

        
        DataBase.getPersons().add(member1);
        DataBase.getPersons().add(member2);
        DataBase.getPersons().add(member3);
        DataBase.getPersons().add(member4);
        DataBase.getPersons().add(member5);
        DataBase.getPersons().add(member6);
        DataBase.getPersons().add(member7);
        DataBase.getPersons().add(member8);
        DataBase.getPersons().add(member9);
        DataBase.getPersons().add(member10);
        DataBase.getPersons().add(member11);
        DataBase.getPersons().add(member12);
        DataBase.getPersons().add(member13);
        DataBase.getPersons().add(member14);
        DataBase.getPersons().add(member15);

        
        DataBase.getPersons().add(trainer1);
        DataBase.getPersons().add(trainer2);
        DataBase.getPersons().add(trainer3);
        DataBase.getPersons().add(trainer4);
        DataBase.getPersons().add(trainer5);
        DataBase.getPersons().add(trainer6);
        DataBase.getPersons().add(trainer7);

        
        DataBase.getSessions().add(personal1);
        DataBase.getSessions().add(personal2);
        DataBase.getSessions().add(personal3);
        DataBase.getSessions().add(personal4);
       

        DataBase.getSessions().add(group1);
        DataBase.getSessions().add(group2);
        DataBase.getSessions().add(group3);
        DataBase.getSessions().add(group4);
        DataBase.getSessions().add(group5);
        DataBase.getSessions().add(group6);
        DataBase.getSessions().add(group7);
        DataBase.getSessions().add(group8);
        DataBase.getSessions().add(group9);
        DataBase.getSessions().add(group10);
        DataBase.getSessions().add(group11);
        DataBase.getSessions().add(group12);
        DataBase.getSessions().add(group13);
        DataBase.getSessions().add(group14);
        DataBase.getSessions().add(group15);
    

        DataBase.getEquipment().add(equipment1);
        DataBase.getEquipment().add(equipment2);
        DataBase.getEquipment().add(equipment3);
        DataBase.getEquipment().add(equipment4);
        DataBase.getEquipment().add(equipment5);
        DataBase.getEquipment().add(equipment6);
        DataBase.getEquipment().add(equipment7);
        DataBase.getEquipment().add(equipment8);
        DataBase.getEquipment().add(equipment9);
        DataBase.getEquipment().add(equipment10);
        DataBase.getEquipment().add(equipment11);
        DataBase.getEquipment().add(equipment12);
        DataBase.getEquipment().add(equipment13);
        DataBase.getEquipment().add(equipment14);
        DataBase.getEquipment().add(equipment15);
        DataBase.getEquipment().add(equipment16);
        DataBase.getEquipment().add(equipment17);
        DataBase.getEquipment().add(equipment18);
        DataBase.getEquipment().add(equipment19);
        DataBase.getEquipment().add(equipment20);
  
        
        /** all the group must have 1 or more trainer **/
        group1.getMembers().add(member1);
        member1.getSessions().add(group1);
        group1.getMembers().add(member2);
        member2.getSessions().add(group1);
        group1.getMembers().add(member3);
        member3.getSessions().add(group1);
        group1.getMembers().add(member10);
        member10.getSessions().add(group1);
        group1.getMembers().add(member5);
        member5.getSessions().add(group1);
        group1.getMembers().add(member6);
        member6.getSessions().add(group1);
        group1.getTrainers().add(trainer1);
        trainer1.getSessions().add(group1);
        
        group2.getMembers().add(member2);
        member2.getSessions().add(group2);
        group2.getTrainers().add(trainer2);
        trainer2.getSessions().add(group2);
                
        group3.getMembers().add(member1);
        member1.getSessions().add(group3);
        group3.getTrainers().add(trainer2);
        trainer2.getSessions().add(group3);
        
        group4.getTrainers().add(trainer2);
        trainer2.getSessions().add(group4);
        
        group5.getMembers().add(member1);
        group5.getMembers().add(member2);
        member1.getSessions().add(group5);
        member2.getSessions().add(group5);
        group5.getTrainers().add(trainer1);
        group5.getTrainers().add(trainer2);
        trainer1.getSessions().add(group5);
        trainer2.getSessions().add(group5);
        

        group6.getTrainers().add(trainer3);
        trainer3.getSessions().add(group6);
    
        group7.getTrainers().add(trainer3);
        group7.getTrainers().add(trainer2);
        group7.getTrainers().add(trainer1);
        trainer3.getSessions().add(group7);
        trainer2.getSessions().add(group7);
        trainer1.getSessions().add(group7);
        
        group8.getMembers().add(member10);
        group8.getMembers().add(member3);
        group8.getMembers().add(member9);
        group8.getMembers().add(member8);
        trainer5.getSessions().add(group8);
        
        group9.getTrainers().add(trainer1);
        trainer1.getSessions().add(group9);
        
        group10.getTrainers().add(trainer2);
        trainer2.getSessions().add(group10);
        
        group11.getMembers().add(member11);
        group11.getMembers().add(member12);
        group11.getTrainers().add(trainer7);

        group12.getMembers().add(member13);
        group12.getMembers().add(member14);
        group12.getTrainers().add(trainer6);

        group13.getMembers().add(member15);
        group13.getMembers().add(member9);
        group13.getTrainers().add(trainer1);

        group14.getMembers().add(member10);
        group14.getMembers().add(member3);
        group14.getTrainers().add(trainer2);

        group15.getMembers().add(member5);
        group15.getMembers().add(member6);
        group15.getTrainers().add(trainer7);
        
        
        personal1.setMember(member1);
        member1.getSessions().add(personal1);

        personal2.setMember(member2);
        member2.getSessions().add(personal2);
     
        /** membership options with the discounts we offer **/
        member1.setMembership(new Membership("DAILY","SOLDIER"));
        member2.setMembership(new Membership("MONTHLY","STUDENT"));
        member3.setMembership(new Membership("YEARLY","ELDERLY"));
        member4.setMembership(new Membership("YEARLY","NONE"));

        
        DataBase.saveDataToFile();
    }
    
}
