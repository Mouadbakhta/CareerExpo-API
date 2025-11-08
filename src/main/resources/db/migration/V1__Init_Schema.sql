CREATE TABLE IF NOT EXISTS etudiants (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    etablissement VARCHAR(255) NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING',
    telephone VARCHAR(20) NOT NULL,
    cv_pdf_path VARCHAR(255) NOT NULL,
    cv_video_path VARCHAR(255),
    competition_id BIGINT NOT NULL,
    niveau VARCHAR(255) NOT NULL
);