package Game.Equipment.Stuff.Medicine;

public class SmallMedicine extends Medicine
{
    public  SmallMedicine()
    {
        this("Small medicine", 30);
    }

    private SmallMedicine(String name, int healPts)
    {
        super(name, healPts);
    }
}
