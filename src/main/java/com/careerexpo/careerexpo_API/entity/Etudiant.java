/**
 * ENTITÉ JPA : Représente un étudiant inscrit à CareerExpo.
 * 
 * CHAMPS :
 * - id (Long)
 * - nom (String)
 * - prenom (String)
 * - niveau (String : "L1", "M1", etc.)
 * - filiere (String : "Génie Informatique", etc.)
 * - cvPath (String : "uploads/cv/Dupont_Jean_123456789.pdf")
 * - dateInscription (LocalDateTime, auto)
 * 
 * TABLE : etudiants
 * 
 * VALIDATION : @NotBlank sur nom, prenom, niveau, filiere
 */