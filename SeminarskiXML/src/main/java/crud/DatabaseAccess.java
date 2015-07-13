package crud;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.Deo1Type;
import model.Deo1Type.PodaciOPodnosiocuPrijave;
import model.Deo1Type.PodaciOVlasnikuSredstava;
import model.Deo2OpisFizickiPrenosivihSredstavaPlacanjaType;
import model.Deo2OpisFizickiPrenosivihSredstavaPlacanjaType.Cekovi;
import model.Deo2OpisFizickiPrenosivihSredstavaPlacanjaType.Drugo;
import model.Deo2OpisFizickiPrenosivihSredstavaPlacanjaType.Novcanice;
import model.Deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType;
import model.Deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType.PrimalacSredstava;
import model.Deo4PodaciOPrevozuType;
import model.Deo4PodaciOPrevozuType.Marsuta;
import model.MyModel;
import model.ObjectFactory;
import model.ObrazacPPS1;
import model.ObrazacPPS1Type;
import model.OsnovneInformacijeType;
import model.ZakljucakType;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xmldb.api.modules.XMLResource;

/**
 * @author <a href="petrovic.aleks@outlook.com">Aleksandar Petrovic</a>
 */

public class DatabaseAccess {

	// vraca naziv dokumenta pod kojim je sacuvan
	private static String saveDocument(ObrazacPPS1Type jaxbElement) throws JAXBException, ParserConfigurationException {
		JAXBContext jc = JAXBContext.newInstance("model");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		Marshaller m = jc.createMarshaller();
		m.marshal(jaxbElement, doc);

		return ExistManager.saveNode(doc);
	}

	public static List<MyModel> getAllDocumentsByCriteria(String broj, Date datumPrijema, String nadlezniOrgan, String granicniPrelaz, String vrstaPrijave) {
		QueryManager qm = new QueryManager(ExistManager.COLLECTION);

		String query = "/ObrazacPPS1";

		query += "[";

		if (!broj.equals(""))
			query += "@Broj='" + broj + "'";

		if (datumPrijema != null) {
			if (!(query.charAt(query.length() - 1) == '['))
				query += " and ";
			query += "OsnovneInformacije/DatumPrijemaPrijave/text()='" + getXmlGregorianCalendarFromDate(datumPrijema).toString().replace("T00:00:00.000", "") + "'";
		}

		if (!nadlezniOrgan.equals("")) {
			if (!(query.charAt(query.length() - 1) == '['))
				query += " and ";
			query += "OsnovneInformacije/NadlezniOrgan/text()='" + nadlezniOrgan + "'";
		}

		if (!granicniPrelaz.equals("")) {
			if (!(query.charAt(query.length() - 1) == '['))
				query += " and ";
			query += "OsnovneInformacije/GranicniPrelaz/text()='" + granicniPrelaz + "'";
		}

		if (vrstaPrijave != null) {
			if (!(query.charAt(query.length() - 1) == '['))
				query += " and ";
			query += "OsnovneInformacije/VrstaPrijave/text()='" + vrstaPrijave + "'";
		}

		query += "]";

		if (query.contains("[]")) {
			query = query.replace("[]", "");
		}

		System.out.println("Query = " + query);

		List<XMLResource> res = qm.performXpath(query);

		List<MyModel> models = new LinkedList<MyModel>();

		try {
			for (int i = 0; i < res.size(); i++) {
				Node doc = res.get(i).getContentAsDOM();
				JAXBContext jc = JAXBContext.newInstance("model");
				Unmarshaller u = jc.createUnmarshaller();

				JAXBElement<ObrazacPPS1> el = u.unmarshal(doc, ObrazacPPS1.class);
				ObrazacPPS1 obrazac = el.getValue();

				models.add(makeModel(obrazac, res.get(i).getDocumentId()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return models;
	}

	public static void removeDocument(String documentId) {
		ExistManager.remove(documentId);
	}

	public static void changeDocument(MyModel myModel) {
		ExistManager.remove(myModel.getDocumentId());
		saveDocument(myModel);
	}

	public static void saveDocument(MyModel mymodel) {
		try {
			ObjectFactory of = new ObjectFactory();
			ObrazacPPS1 root = of.createObrazacPPS1();

			root.setBroj(mymodel.getBroj());

			// OsnovneInformacije
			// ------------------------------------------------------------------------------------------------
			OsnovneInformacijeType osnovneInformacijeType = of.createOsnovneInformacijeType();
			osnovneInformacijeType.setGranicniPrelaz(mymodel.getGranicniPrelaz());
			osnovneInformacijeType.setDatumPrijemaPrijave(getXmlGregorianCalendarFromDate(mymodel.getDatumPrijema()));
			osnovneInformacijeType.setNadlezniOrgan(mymodel.getNadlezniOrgan());
			osnovneInformacijeType.setPrijavaPrenosa(mymodel.getPrijavaPrenosa());
			osnovneInformacijeType.setVrstaPrijave(mymodel.getVrstaPrijave());

			root.setOsnovneInformacije(osnovneInformacijeType);

			// Deo1
			// ------------------------------------------------------------------------------------------------
			Deo1Type deo1Type = of.createDeo1Type();

			// Deo1 - podnosiocPrijave
			PodaciOPodnosiocuPrijave podaciOPodnosiocuPrijave = of.createDeo1TypePodaciOPodnosiocuPrijave();
			model.Deo1Type.PodaciOPodnosiocuPrijave.Adresa adresaP = of.createDeo1TypePodaciOPodnosiocuPrijaveAdresa();
			adresaP.setAdresaStanovanja(mymodel.getAdresaP());
			adresaP.setDrzava(mymodel.getDrzavaP());
			adresaP.setGrad(mymodel.getGradP());
			if (!mymodel.getPostanskiBrojP().equals(""))
				adresaP.setPostanskiBroj(Integer.parseInt(mymodel.getPostanskiBrojP()));
			podaciOPodnosiocuPrijave.setAdresa(adresaP);

			model.Deo1Type.PodaciOPodnosiocuPrijave.OsnovneInformacije osnovneInformacijeP = of.createDeo1TypePodaciOPodnosiocuPrijaveOsnovneInformacije();
			if (mymodel.getDatumRodjenjaP() != null)
				osnovneInformacijeP.setDatumRodjenja(getXmlGregorianCalendarFromDate(mymodel.getDatumRodjenjaP()));
			osnovneInformacijeP.setDrzavljanstvo(mymodel.getDrzavljanstvoP());
			osnovneInformacijeP.setImePrezime(mymodel.getImePrezimeP());
			osnovneInformacijeP.setMestoRodjenja(mymodel.getMestoRodjenjaP());
			osnovneInformacijeP.setZanimanje(mymodel.getZanimanjeP());
			podaciOPodnosiocuPrijave.setOsnovneInformacije(osnovneInformacijeP);

			model.Deo1Type.PodaciOPodnosiocuPrijave.Pasos pasosP = of.createDeo1TypePodaciOPodnosiocuPrijavePasos();
			if (!mymodel.getPasosBrojP().equals(""))
				pasosP.setBroj(Integer.parseInt(mymodel.getPasosBrojP()));
			if (mymodel.getPasosDatumP() != null)
				pasosP.setDatumIzdavanja(getXmlGregorianCalendarFromDate(mymodel.getPasosDatumP()));
			pasosP.setMestoIzdavanja(mymodel.getPasosMestoP());
			podaciOPodnosiocuPrijave.setPasos(pasosP);

			deo1Type.setPodaciOPodnosiocuPrijave(podaciOPodnosiocuPrijave);

			// Deo1 - vlasnikSredstava
			PodaciOVlasnikuSredstava podaciOVlasnikuSredstava = of.createDeo1TypePodaciOVlasnikuSredstava();
			model.Deo1Type.PodaciOVlasnikuSredstava.Adresa adresaV = of.createDeo1TypePodaciOVlasnikuSredstavaAdresa();
			adresaV.setAdresaStanovanja(mymodel.getAdresaV());
			adresaV.setDrzava(mymodel.getDrzavaV());
			adresaV.setGrad(mymodel.getGradV());
			if (!mymodel.getPostanskiBrojV().equals(""))
				adresaV.setPostanskiBroj(Integer.parseInt(mymodel.getPostanskiBrojV()));
			podaciOVlasnikuSredstava.setAdresa(adresaV);

			model.Deo1Type.PodaciOVlasnikuSredstava.OsnovneInformacije osnovneInformacijeV = of.createDeo1TypePodaciOVlasnikuSredstavaOsnovneInformacije();
			if (mymodel.getDatumRodjenjaV() != null)
				osnovneInformacijeV.setDatumRodjenja(getXmlGregorianCalendarFromDate(mymodel.getDatumRodjenjaV()));
			osnovneInformacijeV.setDrzavljanstvo(mymodel.getDrzavljanstvoV());
			osnovneInformacijeV.setImePrezime(mymodel.getImePrezimeV());
			osnovneInformacijeV.setMestoRodjenja(mymodel.getMestoRodjenjaV());
			osnovneInformacijeV.setZanimanje(mymodel.getZanimanjeV());
			podaciOVlasnikuSredstava.setOsnovneInformacije(osnovneInformacijeV);

			model.Deo1Type.PodaciOVlasnikuSredstava.Pasos pasosV = of.createDeo1TypePodaciOVlasnikuSredstavaPasos();
			if (!mymodel.getPasosBrojV().equals(""))
				pasosV.setBroj(Integer.parseInt(mymodel.getPasosBrojV()));
			if (mymodel.getPasosDatumV() != null)
				pasosV.setDatumIzdavanja(getXmlGregorianCalendarFromDate(mymodel.getPasosDatumV()));
			pasosV.setMestoIzdavanja(mymodel.getPasosMestoV());
			podaciOVlasnikuSredstava.setPasos(pasosV);

			deo1Type.setPodaciOVlasnikuSredstava(podaciOVlasnikuSredstava);

			root.setDeo1(deo1Type);

			// Deo2OpisFizickiPrenosivihSredstavaPlacanja
			// ------------------------------------------------------------------------------------------------

			Deo2OpisFizickiPrenosivihSredstavaPlacanjaType deo2OpisFizickiPrenosivihSredstavaPlacanjaType = of.createDeo2OpisFizickiPrenosivihSredstavaPlacanjaType();
			Cekovi cekovi = of.createDeo2OpisFizickiPrenosivihSredstavaPlacanjaTypeCekovi();
			if (!mymodel.getCekoviIznos().equals(""))
				cekovi.setIznos(Float.parseFloat(mymodel.getCekoviIznos()));
			cekovi.setValuta(mymodel.getCekoviValuta());
			deo2OpisFizickiPrenosivihSredstavaPlacanjaType.setCekovi(cekovi);

			Drugo drugo = of.createDeo2OpisFizickiPrenosivihSredstavaPlacanjaTypeDrugo();
			if (!mymodel.getDrugoIznos().equals(""))
				drugo.setIznos(Float.parseFloat(mymodel.getDrugoIznos()));
			drugo.setValuta(mymodel.getDrugoValuta());
			deo2OpisFizickiPrenosivihSredstavaPlacanjaType.setDrugo(drugo);

			Novcanice novcanice = of.createDeo2OpisFizickiPrenosivihSredstavaPlacanjaTypeNovcanice();
			if (!mymodel.getNovcaniceIznos().equals(""))
				novcanice.setIznos(Float.parseFloat(mymodel.getNovcaniceIznos()));
			novcanice.setValuta(mymodel.getNovcaniceValuta());
			deo2OpisFizickiPrenosivihSredstavaPlacanjaType.setNovcanice(novcanice);

			root.setDeo2OpisFizickiPrenosivihSredstavaPlacanja(deo2OpisFizickiPrenosivihSredstavaPlacanjaType);

			// Deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanja
			// ------------------------------------------------------------------------------------------------

			Deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType = of.createDeo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType();
			deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType.setNamenaSredstava(mymodel.getNamenaSredstava());
			deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType.setPoreklo(mymodel.getPoreklo());

			PrimalacSredstava primalacSredstava = of.createDeo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaTypePrimalacSredstava();
			primalacSredstava.setAdresa(mymodel.getAdresaPrimalacSredstava());
			primalacSredstava.setImePrezime(mymodel.getImePrezimePrimalacSredstava());

			deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType.setPrimalacSredstava(primalacSredstava);

			root.setDeo3PorekloINamenaFizickiPrenosivihSredstavaPlacanja(deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType);

			// Deo4PodaciOPrevozuType
			// ------------------------------------------------------------------------------------------------

			Deo4PodaciOPrevozuType deo4PodaciOPrevozuType = of.createDeo4PodaciOPrevozuType();
			deo4PodaciOPrevozuType.setNacinPrevoza(mymodel.getNacinPrevoza());
			deo4PodaciOPrevozuType.setNacinPrevozaDrugi(mymodel.getNacinPrevozaDrugi());

			Marsuta marsuta = of.createDeo4PodaciOPrevozuTypeMarsuta();
			if (!mymodel.getBrojPoseta().equals(""))
				marsuta.setBrojDolazaka(Integer.parseInt(mymodel.getBrojPoseta()));
			marsuta.setDaLiJeOvoVasPrviDolazak(mymodel.getPrviDolazak());
			if (mymodel.getDatumDolaska() != null)
				marsuta.setDatumDolaska(getXmlGregorianCalendarFromDate(mymodel.getDatumDolaska()));
			if (mymodel.getDatumPolaska() != null)
				marsuta.setDatumPolaska(getXmlGregorianCalendarFromDate(mymodel.getDatumPolaska()));
			if (!mymodel.getReferentniBroj().equals(""))
				marsuta.setReferentniBroj(Integer.parseInt(mymodel.getReferentniBroj()));
			marsuta.setTransportnoPreduzece(mymodel.getTransportnoPreduzece());
			marsuta.setZemljaDolaska(mymodel.getZemljaDolaska());
			marsuta.setZemljaPolaska(mymodel.getZemljaPolaska());
			marsuta.setZemljaTranzita(mymodel.getZemljaTranzita());
			deo4PodaciOPrevozuType.setMarsuta(marsuta);

			root.setDeo4PodaciOPrevozu(deo4PodaciOPrevozuType);

			// ZakljucakType
			// ------------------------------------------------------------------------------------------------

			ZakljucakType zakljucakType = of.createZakljucakType();
			zakljucakType.setPotpisPodnosioca(mymodel.getImageURL());
			zakljucakType.setPrimedbeNadleznogOrgana(mymodel.getPrimedba());

			root.setZakljucak(zakljucakType);

			String documentId = saveDocument(root);
			mymodel.setDocumentId(documentId);

		} catch (JAXBException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static XMLGregorianCalendar getXmlGregorianCalendarFromDate(Date date) {
		try {
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(date);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static MyModel makeModel(ObrazacPPS1 obrazacPPS1Type, String documentId) {
		MyModel mymodel = new MyModel(documentId);

		mymodel.setBroj(obrazacPPS1Type.getBroj());

		// OsnovneInformacije
		// ------------------------------------------------------------------------------------------------
		OsnovneInformacijeType osnovneInformacijeType = obrazacPPS1Type.getOsnovneInformacije();
		mymodel.setGranicniPrelaz(osnovneInformacijeType.getGranicniPrelaz());
		mymodel.setDatumPrijema(xmlGregorianCalendartoDate(osnovneInformacijeType.getDatumPrijemaPrijave()));
		mymodel.setNadlezniOrgan(osnovneInformacijeType.getNadlezniOrgan());
		mymodel.setPrijavaPrenosa(osnovneInformacijeType.getPrijavaPrenosa());
		mymodel.setVrstaPrijave(osnovneInformacijeType.getVrstaPrijave());

		// Deo1
		// ------------------------------------------------------------------------------------------------
		Deo1Type deo1Type = obrazacPPS1Type.getDeo1();

		// Deo1 - podnosiocPrijave
		PodaciOPodnosiocuPrijave podaciOPodnosiocuPrijave = deo1Type.getPodaciOPodnosiocuPrijave();
		model.Deo1Type.PodaciOPodnosiocuPrijave.Adresa adresaP = podaciOPodnosiocuPrijave.getAdresa();
		mymodel.setAdresaP(adresaP.getAdresaStanovanja());
		mymodel.setDrzavaP(adresaP.getDrzava());
		mymodel.setGradP(adresaP.getGrad());
		if (adresaP.getPostanskiBroj() != null)
			mymodel.setPostanskiBrojP(adresaP.getPostanskiBroj().toString());

		model.Deo1Type.PodaciOPodnosiocuPrijave.OsnovneInformacije osnovneInformacijeP = podaciOPodnosiocuPrijave.getOsnovneInformacije();
		mymodel.setDatumRodjenjaP(xmlGregorianCalendartoDate(osnovneInformacijeP.getDatumRodjenja()));
		mymodel.setDrzavljanstvoP(osnovneInformacijeP.getDrzavljanstvo());
		mymodel.setImePrezimeP(osnovneInformacijeP.getImePrezime());
		mymodel.setMestoRodjenjaP(osnovneInformacijeP.getMestoRodjenja());
		mymodel.setZanimanjeP(osnovneInformacijeP.getZanimanje());

		model.Deo1Type.PodaciOPodnosiocuPrijave.Pasos pasosP = podaciOPodnosiocuPrijave.getPasos();
		if (pasosP.getBroj() != null)
			mymodel.setPasosBrojP(pasosP.getBroj().toString());
		mymodel.setPasosDatumP(xmlGregorianCalendartoDate(pasosP.getDatumIzdavanja()));
		mymodel.setPasosMestoP(pasosP.getMestoIzdavanja());

		// Deo1 - vlasnikSredstava
		PodaciOVlasnikuSredstava podaciOVlasnikuSredstava = deo1Type.getPodaciOVlasnikuSredstava();
		PodaciOVlasnikuSredstava.Adresa adresaV = podaciOVlasnikuSredstava.getAdresa();
		mymodel.setAdresaV(adresaV.getAdresaStanovanja());
		mymodel.setDrzavaV(adresaV.getDrzava());
		mymodel.setGradV(adresaV.getGrad());
		if (adresaV.getPostanskiBroj() != null)
			mymodel.setPostanskiBrojV(adresaV.getPostanskiBroj().toString());

		model.Deo1Type.PodaciOVlasnikuSredstava.OsnovneInformacije osnovneInformacijeV = podaciOVlasnikuSredstava.getOsnovneInformacije();
		mymodel.setDatumRodjenjaV(xmlGregorianCalendartoDate(osnovneInformacijeV.getDatumRodjenja()));
		mymodel.setDrzavljanstvoV(osnovneInformacijeV.getDrzavljanstvo());
		mymodel.setImePrezimeV(osnovneInformacijeV.getImePrezime());
		mymodel.setMestoRodjenjaV(osnovneInformacijeV.getMestoRodjenja());
		mymodel.setZanimanjeV(osnovneInformacijeV.getZanimanje());

		model.Deo1Type.PodaciOVlasnikuSredstava.Pasos pasosV = podaciOVlasnikuSredstava.getPasos();
		if (pasosV.getBroj() != null)
			mymodel.setPasosBrojV(pasosV.getBroj().toString());
		mymodel.setPasosDatumV(xmlGregorianCalendartoDate(pasosV.getDatumIzdavanja()));
		mymodel.setPasosMestoV(pasosV.getMestoIzdavanja());

		// Deo2OpisFizickiPrenosivihSredstavaPlacanja
		// ------------------------------------------------------------------------------------------------

		Deo2OpisFizickiPrenosivihSredstavaPlacanjaType deo2OpisFizickiPrenosivihSredstavaPlacanjaType = obrazacPPS1Type.getDeo2OpisFizickiPrenosivihSredstavaPlacanja();
		Cekovi cekovi = deo2OpisFizickiPrenosivihSredstavaPlacanjaType.getCekovi();
		if (cekovi.getIznos() != null)
			mymodel.setCekoviIznos(cekovi.getIznos().toString());
		mymodel.setCekoviValuta(cekovi.getValuta());

		Drugo drugo = deo2OpisFizickiPrenosivihSredstavaPlacanjaType.getDrugo();
		if (drugo.getIznos() != null)
			mymodel.setDrugoIznos(drugo.getIznos().toString());
		mymodel.setDrugoValuta(drugo.getValuta());

		Novcanice novcanice = deo2OpisFizickiPrenosivihSredstavaPlacanjaType.getNovcanice();
		if (novcanice.getIznos() != null)
			mymodel.setNovcaniceIznos(novcanice.getIznos().toString());
		mymodel.setNovcaniceValuta(novcanice.getValuta());

		// Deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanja
		// ------------------------------------------------------------------------------------------------

		Deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType = obrazacPPS1Type.getDeo3PorekloINamenaFizickiPrenosivihSredstavaPlacanja();
		mymodel.setNamenaSredstava(deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType.getNamenaSredstava());
		mymodel.setPoreklo(deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType.getPoreklo());

		PrimalacSredstava primalacSredstava = deo3PorekloINamenaFizickiPrenosivihSredstavaPlacanjaType.getPrimalacSredstava();
		mymodel.setAdresaPrimalacSredstava(primalacSredstava.getAdresa());
		mymodel.setImePrezimePrimalacSredstava(primalacSredstava.getImePrezime());

		// Deo4PodaciOPrevozuType
		// ------------------------------------------------------------------------------------------------

		Deo4PodaciOPrevozuType deo4PodaciOPrevozuType = obrazacPPS1Type.getDeo4PodaciOPrevozu();
		mymodel.setNacinPrevoza(deo4PodaciOPrevozuType.getNacinPrevoza());
		mymodel.setNacinPrevozaDrugi(deo4PodaciOPrevozuType.getNacinPrevozaDrugi());

		Marsuta marsuta = deo4PodaciOPrevozuType.getMarsuta();
		if (marsuta.getBrojDolazaka() != null)
			mymodel.setBrojPoseta(marsuta.getBrojDolazaka().toString());
		mymodel.setPrviDolazak(marsuta.getDaLiJeOvoVasPrviDolazak());
		mymodel.setDatumDolaska(xmlGregorianCalendartoDate(marsuta.getDatumDolaska()));
		mymodel.setDatumPolaska(xmlGregorianCalendartoDate(marsuta.getDatumPolaska()));
		if (marsuta.getReferentniBroj() != null)
			mymodel.setReferentniBroj(marsuta.getReferentniBroj().toString());
		mymodel.setTransportnoPreduzece(marsuta.getTransportnoPreduzece());
		mymodel.setZemljaDolaska(marsuta.getZemljaDolaska());
		mymodel.setZemljaPolaska(marsuta.getZemljaPolaska());
		mymodel.setZemljaTranzita(marsuta.getZemljaTranzita());

		// ZakljucakType
		// ------------------------------------------------------------------------------------------------

		ZakljucakType zakljucakType = obrazacPPS1Type.getZakljucak();
		mymodel.setImageURL(zakljucakType.getPotpisPodnosioca());
		mymodel.setPrimedba(zakljucakType.getPrimedbeNadleznogOrgana());

		return mymodel;
	}

	private static Date xmlGregorianCalendartoDate(XMLGregorianCalendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}

}