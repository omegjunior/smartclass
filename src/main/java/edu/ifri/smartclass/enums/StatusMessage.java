package edu.ifri.smartclass.enums;

public enum StatusMessage {
    //Generic errors
    USER_NOT_FOUND("0001", "Aucun utilisateur ne correspond à ces informations de connexions."),
    INTERNAL_SERVER_ERROR("0002", "Une erreur inattendue s'est produite. Contactez l'administrateur système."),
    BAD_REQUEST_ERROR("0003", "Invalid argument, please review your request."),
    NOT_FOUND_ERROR("0005", "Ressource introuvable."),
    RESOURCE_ALREADY_EXIST("0004", "Cette ressource existe deja"),
    NOT_ALLOWED("0006", "Vous n'etes pas autoriser a effetuer cette operation"),
    NO_CONTENT_ERROR("0007", "Aucun élément trouvé."),
    UNABLE_TO_DELETE("0008", "Impossible de supprmer cette ressource"),
    UNABLE_TO_ADD_OR_UPDATE_RESOURCE("0009", "Impossible d'ajouter ou de modifier cette ressource"),
    NAME_EXIST("0010", "Ce nom est deja utilise"),
    USERNAME_OR_EMAIL_EXIST("0011", "Ce nom d'utilisateur ou Email existe deja"),
    UNABLE_TO_GET_FILTER("0012", "Impossible de recupérer la liste des objets correspondants à ces conditions."),
    UNABLE_TO_SEND_EMAIL("0107", "L'e-mail n'a pas pu être envoyé !"),
    UNABLE_TO_FIND_USER("0012", "User not found !");


    private final String code;
    private final String message;

    private StatusMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
