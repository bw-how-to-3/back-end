package local.heftyb.howto.exceptions;

public class ResourceFoundException
    extends RuntimeException
{
    public ResourceFoundException(String message)
    {
        super("Error from THIS How-To Application " + message);
    }
}