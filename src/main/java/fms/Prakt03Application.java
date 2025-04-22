package fms;

import fms.model.*;
import fms.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@SpringBootApplication
public class Prakt03Application {

	private static final Logger log = LoggerFactory.getLogger(Prakt03Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Prakt03Application.class, args);
	}

	// ******************** 1. Variante ********************
	@Component
    static class FmsController implements CommandLineRunner{
		// this will inject the repository
		@Autowired
		FlottenRepository flottenRepository;
		@Autowired
		FahrzeugRepository fahrzeugRepository;
		@Autowired
		NutzerRepository nutzerRepository;
		@Autowired
		MissionRepository missionRepository;
		@Autowired
		RouteRepository routeRepository;
		@Autowired
		AbteilungRepository abteilungRepository;
		@Autowired
		MitarbeiterRepository mitarbeiterRepository;

		@Override
		public void run(String... args) throws Exception {
			// Abteilungen & Mitarbeitern erstellen
			Abteilung a1 = new Abteilung(1l,"Abteilung 1");
			Abteilung a2 = new Abteilung(2l,"Abteilung 2");

			abteilungRepository.save(a1);
			abteilungRepository.save(a2);

			mitarbeiterRepository.save(new Mitarbeiter(1l,"M1", a1));
			mitarbeiterRepository.save(new Mitarbeiter(2l,"M2", a2));

			// Flotte erstellen
			Flotte fl1 = new Flotte(1, "Alpha","Dresden");
			Flotte fl2 = new Flotte(2, "Beta","Berlin");
			flottenRepository.save(fl1);
			flottenRepository.save(fl2);

			// Fahrzeuge erstellt in Verbindung mit der Klasse "Flotte"
			Fahrzeug fz1 = new Fahrzeug( 1, 300);
			Fahrzeug fz2 = new Fahrzeug(2, 750);
			Fahrzeug fz3 = new Fahrzeug( 3, 650);
			fahrzeugRepository.save(fz1);
			fahrzeugRepository.save(fz2);
			fahrzeugRepository.save(fz3);

			fz1.setFlotte(fl1);
			fz2.setFlotte(fl1);
			fz3.setFlotte(fl2);
			fahrzeugRepository.save(fz1);
			fahrzeugRepository.save(fz2);
			fahrzeugRepository.save(fz3);

			// Nutzer erstellt in Verbindung mit der Klasse "Fahrzeug"
			Nutzer n1 = new Nutzer(1, "Robert");
			Nutzer n2 = new Nutzer(2, "Micha");
			nutzerRepository.save(n1);
			nutzerRepository.save(n2);
			n1.setFahrzeug(fz1);// in die Liste hinzufügen!
			n2.setFahrzeug(fz2);
			nutzerRepository.save(n1);
			nutzerRepository.save(n2);

			// Mission erstellt in Verbindung mit Klasse "Fahrzeug"
			LocalDate startDate1 = LocalDate.of(2024, Month.MAY,10);
			LocalDate endDate1 = LocalDate.of(2024, Month.MAY, 30);
			Mission m1 = new Mission(1, "Transport",startDate1,endDate1);

			LocalDate startDate2 = LocalDate.of(2024, 4,10);
			LocalDate endDate2 = LocalDate.of(2024, 6, 30);
			Mission m2 = new Mission(2, "Umzug",startDate2,endDate2);

			LocalDate startDate3 = LocalDate.of(2024, 6,1);
			LocalDate endDate3 = LocalDate.of(2024, 8, 30);
			Mission m3 = new Mission(3, "Montage",startDate3,endDate3);
			missionRepository.save(m1);
			missionRepository.save(m2);
			missionRepository.save(m3);
			m1.setFahrzeug(fz1);
			m2.setFahrzeug(fz2);
			m3.setFahrzeug(fz1);
			missionRepository.save(m1);
			missionRepository.save(m2);
			missionRepository.save(m3);

			// Route erstellt in Verbindung mit Klasse "Mission"
			Route r1 = new Route(1, "Leipzig","Milan");
			Route r2 = new Route(2, "Hamburg","Luxemburg");
			Route r3 = new Route(3,"Berlin", "Hannover");
			routeRepository.save(r1);
			routeRepository.save(r2);
			routeRepository.save(r3);

			r1.addPoint(new Point(50,250));
			r1.addPoint(new Point(300,350));
			r2.addPoint(new Point(50,100));
			r2.addPoint(new Point(400,500));
			r3.addPoint(new Point(130,500));
			r3.addPoint(new Point(600, 70));
			routeRepository.save(r1);
			routeRepository.save(r2);
			routeRepository.save(r3);

			m1.setRoute(r1);
			m2.setRoute(r2);
			m3.setRoute(r3);
			missionRepository.save(m1);
			missionRepository.save(m2);
			missionRepository.save(m3);

			log.info("");
			log.info("##### START #####");
            log.info("============== Fahrzeuge in der Flotte \"{}\" =================", fl1.getName());
			Set<Fahrzeug> flotte_1 =  fl1.getFahrzeugeList();
			for(Fahrzeug f : flotte_1){
				log.info(f.toString());
			}
            log.info("Anzahl der Fahrzeuge in \"{}\": {}", fl1.getName(), fl1.getAnzahlFahrzeuge());

			Set<Nutzer> fahrzeug_1 = fz1.getNutzerList();
			for(Nutzer n : fahrzeug_1){
				log.info(n.toString());
			}
            log.info("Anzahl der Nutzer in \"{}\": {}", fz1.getFahrzeugId(), fz1.getAnzahlNutzer());

			Set<Nutzer> fahrzeug_2 = fz2.getNutzerList();
			for(Nutzer n : fahrzeug_2){
				log.info(n.toString());
			}
            log.info("Anzahl der Nutzer in \"{}\": {}", fz2.getFahrzeugId(), fz2.getAnzahlNutzer());

			ArrayList<Point> route1 = r1.getPointList();
			log.info("Route ID: {}", r1.getRouteId());
			for(Point p : route1){
                log.info("{}, Standort: {}", r1.toString(), p.toString());
			}

			ArrayList<Point> route2 = r2.getPointList();
            log.info("Route ID: {}", r2.getRouteId());
			for(Point p : route2){
                log.info("{}, Standort: {}", r2.toString(), p.toString());
			}

			log.info("============== Flotten =================");
			log.info("***** findAll *****");
			for(Flotte f : flottenRepository.findAll()){
				log.info(f.toString());
			}
			log.info("***** findByName *****");
			Flotte fl_id = flottenRepository.findByName("Alpha");
			log.info(fl_id.toString());

			log.info("***** findByDienstOrt *****");// andere Variante für Ausgabe!!
			//log.info(flottenRepository.findByDienstOrt("Dresden").toString());
			for (Flotte f_name : flottenRepository.findByDienstOrt("Dresden")){
				log.info(f_name.toString());
			}
			log.info("***** findByFlotteId *****");
			log.info(flottenRepository.findByFlotteId(1).toString());

			log.info("============== Fahrzeuge =================");
			log.info("***** findAll *****");
			for(Fahrzeug f_all : fahrzeugRepository.findAll()){
				log.info(f_all.toString());
			}
			log.info("***** findByFahrzeugId *****");
			Optional<Fahrzeug> f_id = fahrzeugRepository.findByFahrzeugId(1);
			log.info(f_id.toString());
			log.info("***** findByLadungKapazitaetGreaterThan *****");
			for(Fahrzeug f_kap : fahrzeugRepository.findByLadungKapazitaetGreaterThan(600)){
				log.info(f_kap.toString());
			}
			log.info("*****");
            log.info("Anzahl der Missiones für ein Fahrzeug ({}): {}", fz1.getFahrzeugId(), fz1.getAnzahlMission());
			log.info("*****");
			Set<Mission> missionFuerFahrzeug = fz1.getMissionList();
			for (Mission m : missionFuerFahrzeug){
				log.info(m.toString());
			}
			log.info("============== Nutzern =================");
			log.info("***** findAll *****");
			for(Nutzer N_all : nutzerRepository.findAll()){
				log.info(N_all.toString());
			}
			log.info("***** findByNutzerId *****");
			Nutzer n_id = nutzerRepository.findByNutzerId(2);
			log.info(n_id.toString());
			log.info("***** findByName *****");
			for(Nutzer n_name : nutzerRepository.findByName("Robert")){
				log.info(n_name.toString());
			}

			log.info("============== Missionen =================");
			log.info("***** findAll *****");
			for(Mission m : missionRepository.findAll()){
				log.info(m.toString());
			}
			log.info("***** findByMissionId *****");
			Optional<Mission> m_id = missionRepository.findByMissionId(1);
			log.info(m_id.toString());
			log.info("***** findByMissionName *****");
			for (Mission m : missionRepository.findByMissionName("Umzug")) {
				log.info(m.toString());
			}
			log.info("*****");
			for(String a : List.of("Route für Mission (" + m1.getMissionName() + "): " + m1.getRoute())){
				log.info(a);
			}
			log.info("*****");
			for(String a : List.of("Route für Mission (" + m2.getMissionName() + "): " + m2.getRoute())){
				log.info(a);
			}
			log.info("*****");
			log.info("============== Routen =================");
			log.info("***** findAll *****");
			for(Route r : routeRepository.findAll()){
				log.info(r.toString());
			}
			log.info("*****");
			log.info("##### END #####");
			log.info("");
		}
	}
	// ******************** 2. Variante ********************
//	@Bean
//	public CommandLineRunner demo(FlottenRepository flottenRepository, FahrzeugRepository fahrzeugRepository, NutzerRepository nutzerRepository,
//								  MissionRepository missionRepository, RouteRepository routeRepository){
//
//		return args ->  {
//			// Flotte erstellen
//			Flotte fl1 = new Flotte("Alpha","Dresden");
//			Flotte fl2 = new Flotte("Beta","Berlin");
//			flottenRepository.save(fl1);
//			flottenRepository.save(fl2);
//
//			// Fahrzeuge erstellt in Verbindung mit der Klasse "Flotte"
//			Fahrzeug fz1 = new Fahrzeug( 300);
//			Fahrzeug fz2 = new Fahrzeug(750);
//			Fahrzeug fz3 = new Fahrzeug( 650);
//			fahrzeugRepository.save(fz1);
//			fahrzeugRepository.save(fz2);
//			fahrzeugRepository.save(fz3);
//			fl1.setFahrzeuge(fz1);
//			fl1.setFahrzeuge(fz2);
//
//			// Nutzer erstellt in Verbindung mit der Klasse "Fahrzeug"
//			Nutzer n1 = new Nutzer("Robert");
//			Nutzer n2 = new Nutzer("Micha");
//			nutzerRepository.save(n1);
//			nutzerRepository.save(n2);
//			fz1.setNutzer(n1);
//			fz2.setNutzer(n2);
//
//			// Mission erstellt in Verbindung mit Klasse "Fahrzeug"
//			LocalDate startDate1 = LocalDate.of(2024, Month.MAY,10);
//			LocalDate endDate1 = LocalDate.of(2024, Month.MAY, 30);
//			Mission m1 = new Mission("Transport",startDate1,endDate1);
//			missionRepository.save(m1);
//			m1.setFahrzeugList(fz1);
//			m1.setFahrzeugList(fz2);
//
//
//			LocalDate startDate2 = LocalDate.of(2024, 04,10);
//			LocalDate endDate2 = LocalDate.of(2024, 06, 30);
//			Mission m2 = new Mission("Umzug",startDate2,endDate2);
//			missionRepository.save(m2);
//			m2.setFahrzeugList(fz3);
//
//
//			// Route erstellt in Verbindung mit Klasse "Mission"
//			Route r1 = new Route("Leipzig","Milan");
//			Route r2 = new Route("Hamburg","Luxemburg");
//			routeRepository.save(r1);
//			routeRepository.save(r2);
//			r1.setMission(m1);
//			r2.setMission(m2);
//
//			log.info("===== START =====");
//			log.info("============== Fahrzeuge in der Flotte \"" + fl1.getName() + "\" =================");
//			Set<Fahrzeug> flotte_1 =  fl1.getFahrzeuge();
//			for(Fahrzeug f : flotte_1){
//				log.info(f.toString());
//			}
//			log.info("Anzahl der Fahrzeuge in \"" + fl1.getName() + "\": " + fl1.getAnzahlFahrzeuge());
//			Set<Nutzer> fahrzeug_1 = fz1.getNutzerList();
//			for(Nutzer n : fahrzeug_1){
//				log.info(n.toString());
//			}
//			log.info("Anzahl der Nutzer in \"" + fz1.getFahrzeugId() + "\": " + fz1.getAnzahlNutzer());
//			Set<Nutzer> fahrzeug_2 = fz2.getNutzerList();
//			for(Nutzer n : fahrzeug_2){
//				log.info(n.toString());
//			}
//			log.info("Anzahl der Nutzer in \"" + fz2.getFahrzeugId() + "\": " + fz2.getAnzahlNutzer());
//
//			log.info("============== Flotten =================");
//			log.info("***** findAll *****");
//			for(Flotte f : flottenRepository.findAll()){
//				log.info(f.toString());
//			}
//			log.info("***** findByName *****");
//			Flotte fl_id = flottenRepository.findByName("Alpha");
//			log.info(fl_id.toString());
//
//			log.info("***** findByDienstOrt *****");// andere Variante für Ausgabe!!
//			//log.info(flottenRepository.findByDienstOrt("Dresden").toString());
//			for (Flotte f_name : flottenRepository.findByDienstOrt("Dresden")){
//				log.info(f_name.toString());
//			}
//			log.info("***** findByFlotteId *****");
//			log.info(flottenRepository.findByFlotteId(1).toString());
//
//			log.info("============== Fahrzeuge =================");
//			log.info("***** findAll *****");
//			for(Fahrzeug f_all : fahrzeugRepository.findAll()){
//				log.info(f_all.toString());
//			}
//			log.info("***** findByFahrzeugId *****");
//			Fahrzeug f_id = fahrzeugRepository.findByFahrzeugId(1);
//			log.info(f_id.toString());
//			log.info("***** findByLadungKapazitaetGreaterThan *****");
//			for(Fahrzeug f_kap : fahrzeugRepository.findByLadungKapazitaetGreaterThan(600)){
//				log.info(f_kap.toString());
//			}
//			log.info("============== Nutzern =================");
//			log.info("***** findAll *****");
//			for(Nutzer N_all : nutzerRepository.findAll()){
//				log.info(N_all.toString());
//			}
//			log.info("***** findByNutzerId *****");
//			Nutzer n_id = nutzerRepository.findByNutzerId(2);
//			log.info(n_id.toString());
//			log.info("***** findByName *****");
//			for(Nutzer n_name : nutzerRepository.findByName("Robert")){
//				log.info(n_name.toString());
//			}
//
//			log.info("============== Missionen =================");
//			log.info("***** findAll *****");
//			for(Mission m : missionRepository.findAll()){
//				log.info(m.toString());
//			}
//			log.info("***** findByMissionId *****");
//			Mission m_id = missionRepository.findByMissionId(1);
//			log.info(m_id.toString());
//			log.info("***** findByMissionName *****");
//			for (Mission m : missionRepository.findByMissionName("Umzug")){
//				log.info(m.toString());
//			}
//			log.info("*****");
//			log.info("Anzahl der Fahrzeuge in Mission (" + m1.getMissionId() + "): " + m1.getAnzahlFahrzeuge());
//			log.info("*****");
//			Set<Fahrzeug> fahrInMission = m2.getFahrzeugList();
//			for (Fahrzeug f : fahrInMission){
//				log.info(f.toString());
//			}
//			log.info("============== Routen =================");
//			log.info("***** findAll *****");
//			for(Route r : routeRepository.findAll()){
//				log.info(r.toString());
//			}
//			log.info("*****");
//			log.info("===== END =====");
//
//			//! TODO: Mission richtig impl., mit Fahrzeug und Route verbinden (siehe Diagram auf Ipad) Done!
//			//! TODO: Route --"--, mit Mission verbinden Done!
//			//# TODO: REST API anwenden noch zu impl.
///*
//			// Alle Fahrzeuge anzeigen
//			log.info("======== Fahrzeuge:");
//			for(Fahrzeug f : fahrzeugRepository.findAll()){
//				log.info(f.toString());
//			}
//			log.info("");
//
//
//			// Alle Fahrzeuge anzeigen
//			log.info("======== Fahrzeuge:");
//			for(Fahrzeug fahrzeug1 : fahrzeugRepository.findAll()){
//				log.info(fahrzeug1.toString());
//			}
//			log.info("");
//
//			log.info("======== Fahrzeuge gefunden durch findById(001):");
//			// Ein best. Fahr. anzeigen
//			Fahrzeug fahrzeug2 = fahrzeugRepository.findById(001);
//			log.info("Fahrzeug found with: ");
//			log.info(fahrzeug2.toString());
//			log.info("");
//
//			log.info("======== Fahrzeuge gefunden durch findByLadungKapazität(.):");
//			for (Fahrzeug fahrzeug3 : fahrzeugRepository.findByLadungKapazitätGreaterThan(500)){
//				log.info(fahrzeug3.toString());
//			}
//			log.info("");
// */
//		};
//	}
}
