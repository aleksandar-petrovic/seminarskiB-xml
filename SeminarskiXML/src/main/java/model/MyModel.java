package model;

import java.util.Date;

/**
 * @author <a href="petrovic.aleks@outlook.com">Aleksandar Petrovic</a>
 */

public class MyModel {

	public MyModel() {
		// TODO Auto-generated constructor stub
	}

	public MyModel(String documentId) {
		this.documentId = documentId;
	}

	String documentId;

	String broj;
	Date datumPrijema;
	String prijavaPrenosa;
	String nadlezniOrgan;
	String granicniPrelaz;
	String vrstaPrijave;

	String imePrezimeP;
	String imePrezimeV;
	String drzavljanstvoP;
	String drzavljanstvoV;
	Date datumRodjenjaP;
	Date datumRodjenjaV;
	String mestoRodjenjaP;
	String mestoRodjenjaV;
	String zanimanjeP;
	String zanimanjeV;
	String adresaP;
	String adresaV;
	String gradP;
	String gradV;
	String postanskiBrojP;
	String postanskiBrojV;
	String drzavaP;
	String drzavaV;

	String pasosBrojP;
	String pasosBrojV;
	Date pasosDatumP;
	Date pasosDatumV;
	String pasosMestoP;
	String pasosMestoV;

	String novcaniceIznos;
	String novcaniceValuta;
	String cekoviIznos;
	String cekoviValuta;
	String drugoIznos;
	String drugoValuta;

	String poreklo;
	String imePrezimePrimalacSredstava;
	String adresaPrimalacSredstava;
	String namenaSredstava;

	String nacinPrevoza;
	String nacinPrevozaDrugi;
	String zemljaPolaska;
	String zemljaTranzita;
	String zemljaDolaska;
	Date datumPolaska;
	Date datumDolaska;
	String transportnoPreduzece;
	String referentniBroj;
	String prviDolazak;
	String brojPoseta;
	String primedba;

	String imageURL;

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != getClass())
			return false;
		if (obj == this)
			return true;
		MyModel model = (MyModel) obj;
		return model.getDocumentId().equals(getDocumentId());
	}

	@Override
	public int hashCode() {
		return getDocumentId().hashCode();
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public Date getDatumPrijema() {
		return datumPrijema;
	}

	public void setDatumPrijema(Date datumPrijema) {
		this.datumPrijema = datumPrijema;
	}

	public String getPrijavaPrenosa() {
		return prijavaPrenosa;
	}

	public void setPrijavaPrenosa(String prijavaPrenosa) {
		this.prijavaPrenosa = prijavaPrenosa;
	}

	public String getNadlezniOrgan() {
		return nadlezniOrgan;
	}

	public void setNadlezniOrgan(String nadlezniOrgan) {
		this.nadlezniOrgan = nadlezniOrgan;
	}

	public String getGranicniPrelaz() {
		return granicniPrelaz;
	}

	public void setGranicniPrelaz(String granicniPrelaz) {
		this.granicniPrelaz = granicniPrelaz;
	}

	public String getVrstaPrijave() {
		return vrstaPrijave;
	}

	public void setVrstaPrijave(String vrstaPrijave) {
		this.vrstaPrijave = vrstaPrijave;
	}

	public String getImePrezimeP() {
		return imePrezimeP;
	}

	public void setImePrezimeP(String imePrezimeP) {
		this.imePrezimeP = imePrezimeP;
	}

	public String getImePrezimeV() {
		return imePrezimeV;
	}

	public void setImePrezimeV(String imePrezimeV) {
		this.imePrezimeV = imePrezimeV;
	}

	public String getDrzavljanstvoP() {
		return drzavljanstvoP;
	}

	public void setDrzavljanstvoP(String drzavljanstvoP) {
		this.drzavljanstvoP = drzavljanstvoP;
	}

	public String getDrzavljanstvoV() {
		return drzavljanstvoV;
	}

	public void setDrzavljanstvoV(String drzavljanstvoV) {
		this.drzavljanstvoV = drzavljanstvoV;
	}

	public Date getDatumRodjenjaP() {
		return datumRodjenjaP;
	}

	public void setDatumRodjenjaP(Date datumRodjenjaP) {
		this.datumRodjenjaP = datumRodjenjaP;
	}

	public Date getDatumRodjenjaV() {
		return datumRodjenjaV;
	}

	public void setDatumRodjenjaV(Date datumRodjenjaV) {
		this.datumRodjenjaV = datumRodjenjaV;
	}

	public String getMestoRodjenjaP() {
		return mestoRodjenjaP;
	}

	public void setMestoRodjenjaP(String mestoRodjenjaP) {
		this.mestoRodjenjaP = mestoRodjenjaP;
	}

	public String getMestoRodjenjaV() {
		return mestoRodjenjaV;
	}

	public void setMestoRodjenjaV(String mestoRodjenjaV) {
		this.mestoRodjenjaV = mestoRodjenjaV;
	}

	public String getZanimanjeP() {
		return zanimanjeP;
	}

	public void setZanimanjeP(String zanimanjeP) {
		this.zanimanjeP = zanimanjeP;
	}

	public String getZanimanjeV() {
		return zanimanjeV;
	}

	public void setZanimanjeV(String zanimanjeV) {
		this.zanimanjeV = zanimanjeV;
	}

	public String getAdresaP() {
		return adresaP;
	}

	public void setAdresaP(String adresaP) {
		this.adresaP = adresaP;
	}

	public String getAdresaV() {
		return adresaV;
	}

	public void setAdresaV(String adresaV) {
		this.adresaV = adresaV;
	}

	public String getGradP() {
		return gradP;
	}

	public void setGradP(String gradP) {
		this.gradP = gradP;
	}

	public String getGradV() {
		return gradV;
	}

	public void setGradV(String gradV) {
		this.gradV = gradV;
	}

	public String getPostanskiBrojP() {
		return postanskiBrojP;
	}

	public void setPostanskiBrojP(String postanskiBrojP) {
		this.postanskiBrojP = postanskiBrojP;
	}

	public String getPostanskiBrojV() {
		return postanskiBrojV;
	}

	public void setPostanskiBrojV(String postanskiBrojV) {
		this.postanskiBrojV = postanskiBrojV;
	}

	public String getDrzavaP() {
		return drzavaP;
	}

	public void setDrzavaP(String drzavaP) {
		this.drzavaP = drzavaP;
	}

	public String getDrzavaV() {
		return drzavaV;
	}

	public void setDrzavaV(String drzavaV) {
		this.drzavaV = drzavaV;
	}

	public String getPasosBrojP() {
		return pasosBrojP;
	}

	public void setPasosBrojP(String pasosBrojP) {
		this.pasosBrojP = pasosBrojP;
	}

	public String getPasosBrojV() {
		return pasosBrojV;
	}

	public void setPasosBrojV(String pasosBrojV) {
		this.pasosBrojV = pasosBrojV;
	}

	public Date getPasosDatumP() {
		return pasosDatumP;
	}

	public void setPasosDatumP(Date pasosDatumP) {
		this.pasosDatumP = pasosDatumP;
	}

	public Date getPasosDatumV() {
		return pasosDatumV;
	}

	public void setPasosDatumV(Date pasosDatumV) {
		this.pasosDatumV = pasosDatumV;
	}

	public String getPasosMestoP() {
		return pasosMestoP;
	}

	public void setPasosMestoP(String pasosMestoP) {
		this.pasosMestoP = pasosMestoP;
	}

	public String getPasosMestoV() {
		return pasosMestoV;
	}

	public void setPasosMestoV(String pasosMestoV) {
		this.pasosMestoV = pasosMestoV;
	}

	public String getNovcaniceIznos() {
		return novcaniceIznos;
	}

	public void setNovcaniceIznos(String novcaniceIznos) {
		this.novcaniceIznos = novcaniceIznos;
	}

	public String getNovcaniceValuta() {
		return novcaniceValuta;
	}

	public void setNovcaniceValuta(String novcaniceValuta) {
		this.novcaniceValuta = novcaniceValuta;
	}

	public String getCekoviIznos() {
		return cekoviIznos;
	}

	public void setCekoviIznos(String cekoviIznos) {
		this.cekoviIznos = cekoviIznos;
	}

	public String getCekoviValuta() {
		return cekoviValuta;
	}

	public void setCekoviValuta(String cekoviValuta) {
		this.cekoviValuta = cekoviValuta;
	}

	public String getDrugoIznos() {
		return drugoIznos;
	}

	public void setDrugoIznos(String drugoIznos) {
		this.drugoIznos = drugoIznos;
	}

	public String getDrugoValuta() {
		return drugoValuta;
	}

	public void setDrugoValuta(String drugoValuta) {
		this.drugoValuta = drugoValuta;
	}

	public String getPoreklo() {
		return poreklo;
	}

	public void setPoreklo(String poreklo) {
		this.poreklo = poreklo;
	}

	public String getImePrezimePrimalacSredstava() {
		return imePrezimePrimalacSredstava;
	}

	public void setImePrezimePrimalacSredstava(String imePrezimePrimalacSredstava) {
		this.imePrezimePrimalacSredstava = imePrezimePrimalacSredstava;
	}

	public String getAdresaPrimalacSredstava() {
		return adresaPrimalacSredstava;
	}

	public void setAdresaPrimalacSredstava(String adresaPrimalacSredstava) {
		this.adresaPrimalacSredstava = adresaPrimalacSredstava;
	}

	public String getNamenaSredstava() {
		return namenaSredstava;
	}

	public void setNamenaSredstava(String namenaSredstava) {
		this.namenaSredstava = namenaSredstava;
	}

	public String getNacinPrevoza() {
		return nacinPrevoza;
	}

	public void setNacinPrevoza(String nacinPrevoza) {
		this.nacinPrevoza = nacinPrevoza;
	}

	public String getNacinPrevozaDrugi() {
		return nacinPrevozaDrugi;
	}

	public void setNacinPrevozaDrugi(String nacinPrevozaDrugi) {
		this.nacinPrevozaDrugi = nacinPrevozaDrugi;
	}

	public String getZemljaPolaska() {
		return zemljaPolaska;
	}

	public void setZemljaPolaska(String zemljaPolaska) {
		this.zemljaPolaska = zemljaPolaska;
	}

	public String getZemljaTranzita() {
		return zemljaTranzita;
	}

	public void setZemljaTranzita(String zemljaTranzita) {
		this.zemljaTranzita = zemljaTranzita;
	}

	public String getZemljaDolaska() {
		return zemljaDolaska;
	}

	public void setZemljaDolaska(String zemljaDolaska) {
		this.zemljaDolaska = zemljaDolaska;
	}

	public Date getDatumPolaska() {
		return datumPolaska;
	}

	public void setDatumPolaska(Date datumPolaska) {
		this.datumPolaska = datumPolaska;
	}

	public Date getDatumDolaska() {
		return datumDolaska;
	}

	public void setDatumDolaska(Date datumDolaska) {
		this.datumDolaska = datumDolaska;
	}

	public String getTransportnoPreduzece() {
		return transportnoPreduzece;
	}

	public void setTransportnoPreduzece(String transportnoPreduzece) {
		this.transportnoPreduzece = transportnoPreduzece;
	}

	public String getReferentniBroj() {
		return referentniBroj;
	}

	public void setReferentniBroj(String referentniBroj) {
		this.referentniBroj = referentniBroj;
	}

	public String getPrviDolazak() {
		return prviDolazak;
	}

	public void setPrviDolazak(String prviDolazak) {
		this.prviDolazak = prviDolazak;
	}

	public String getBrojPoseta() {
		return brojPoseta;
	}

	public void setBrojPoseta(String brojPoseta) {
		this.brojPoseta = brojPoseta;
	}

	public String getPrimedba() {
		return primedba;
	}

	public void setPrimedba(String primedba) {
		this.primedba = primedba;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

}
