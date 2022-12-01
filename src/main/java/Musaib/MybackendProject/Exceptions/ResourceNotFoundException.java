package Musaib.MybackendProject.Exceptions;


public class ResourceNotFoundException extends  RuntimeException{
    String resourceName;
    String fieldName;
    int fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, int fieldValue) {
        super(String.format("%s not found with %s: %d",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
