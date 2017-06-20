package it.uniroma3.progetto.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"titolo","autore_id"}))
public class Quadro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Integer anno;

	@Column(unique = true)
	@NotEmpty
	@NotNull
	private String titolo;

	@NotEmpty
	@NotNull
	private String tecnica;

	@NotEmpty
	private String dimensioni;

//	@Lob
//	private byte[] file;
//	;
	@ManyToOne
	private Autore autore;


	protected Quadro() {}


//	public byte[] getFile() {
//		return file;
//	}
//
//
//	public void setFile(byte[] file) {
//		this.file = file;
//	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public String getTecnica() {
		return tecnica;
	}

	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
	}

	public String getDimensioni() {
		return dimensioni;
	}

	public void setDimensioni(String dimensioni) {
		this.dimensioni = dimensioni;
	}
	public Autore getAutore() {
		return autore;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	//	public ImmagineQuadro getImmagine() {
	//		return immagine;
	//	}
	//
	//	public void setImmagine(ImmagineQuadro immagine) {
	//		this.immagine = immagine;
	//	}
	//	



}
