-- Supprimer l'ancienne colonne cv_path si elle existe
ALTER TABLE etudiants DROP COLUMN IF EXISTS cv_path;

-- Vérifier si les nouvelles colonnes existent, sinon les créer
ALTER TABLE etudiants 
ADD COLUMN IF NOT EXISTS cv_pdf_path VARCHAR(255) NOT NULL,
ADD COLUMN IF NOT EXISTS cv_video_path VARCHAR(255);

-- Ajouter la colonne téléphone si elle n'existe pas
ALTER TABLE etudiants 
ADD COLUMN IF NOT EXISTS telephone VARCHAR(20) NOT NULL;