; Copyright (c) 2012 Fabien Bourgeois
; See the file LICENSE for copying permission.

(ns cyasus.lang)

(def labels {"EN" {:WRONG_URL "Wrong URL : "
                  :KEY_USED "Key already used, please chose another one."
                  :KEY_INVALID "Key is invalid. We can't redirect you."
                  :KEY_DONT_EXISTS "Key doesn't exist."
                  :VKEY_INVALID "Verification key is invalid. You can't delete the link."
                  :LINK_SUCCESS_RM "Link successfully removed."
                  :LABEL_GENERATE "Generate !"
                  :LABEL_KEY "Key (optional, 10 characters max)"
                  :LABEL_LINK "URL"
                  :LINK_LIST "List of already stored links"
                  :LINK_LIST_EMPTY "No link stored yet"
                  :POST_SUCCESS "Congratulations, your link has been successfully shortened."
                  :POST_GENERATED_INTRO "Here are the generated URLs:"
                  :POST_GENERATED_SHARE "Share this link : "
                  :POST_GENERATED_DELETE "Delete the link from the database with : "
                  :GO_TO_LINK_PRE "The link you have reached points to "
                  :GO_TO_LINK_POST "Automatic redirection is not activated on this website. You must click if you want to follow the link."
                  :ERROR_KEY_LEN "Key must have minimum two characters and maximum ten."
                  :ERROR_URL "URL is invalid : it must be an FTP, HTTP(S) one."}
             "FR" {:WRONG_URL "Lien malformé : "
                  :KEY_USED "La clé est déjà utilisée, veuillez en choisir une nouvelle."
                  :KEY_INVALID "La clé n'est pas valide. Nous ne pouvons pas vous rediriger."
                  :KEY_DONT_EXISTS "La clé n'existe pas."
                  :VKEY_INVALID "La clé de vérification n'est pas valide : vous ne pouvez pas supprimer ce lien."
                  :LINK_SUCCESS_RM "Lien supprimé avec succès."
                  :LABEL_GENERATE "Générer !"
                  :LABEL_KEY "Clé (optionelle, 10 caractères maximum)"
                  :LABEL_LINK "URL"
                  :LINK_LIST "Liste des hyperliens en base de données"
                  :LINK_LIST_EMPTY "Aucun hyperlien en base"
                  :POST_SUCCESS "Félicitations, votre lien a été raccourci avec succès."
                  :POST_GENERATED_INTRO "Voici les URLs générés :"
                  :POST_GENERATED_SHARE "Partagez ce lien : "
                  :POST_GENERATED_DELETE "Supprimer le lien de la base de données grâce à : "
                  :GO_TO_LINK_PRE "Le lien qu'on vous a donné pointe vers : "
                  :GO_TO_LINK_POST "La redirection automatique est désactivée sur ce site, vous devez cliquer sur le lien si vous souhaitez le suivre."
                  :ERROR_KEY_LEN "La clé doit comporter au moins deux caractères et au plus dix."
                  :ERROR_URL "L'URL est invalide : ce doit être une adresse de type HTTP(S) ou FTP."}})
