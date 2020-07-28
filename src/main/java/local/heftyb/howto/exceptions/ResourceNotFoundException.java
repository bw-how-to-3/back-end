package local.heftyb.howto.exceptions;

public class ResourceNotFoundException
    extends RuntimeException
{
    public ResourceNotFoundException(String message)
    {
        super("Error from THIS How-To Application " + message);
    }
}