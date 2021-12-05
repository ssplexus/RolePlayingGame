package Game.Equipment;

import java.util.Objects;

public class Equipment
{
    private final String name;
    private final String eqType;
    private final String propertyName;
    private final int propertyPoints;

    protected Equipment(String name, String eqType, String propertyName, int propertyPoints)
    {
        this.name = name;
        this.eqType = eqType;
        this.propertyName = propertyName;
        this.propertyPoints = propertyPoints;
    }

    public String getName() {
        return name;
    }

    public String getEqType() {
        return eqType;
    }

    public int getPropertyPoints() {
        return propertyPoints;
    }

    @Override
    public String toString()
    {
        return new StringBuilder().append(name + " :").
                append("[type = " + eqType  + "]").
                append("[" + propertyName + " = " + propertyPoints + "]").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return propertyPoints == equipment.propertyPoints && name.equals(equipment.name) && eqType.equals(equipment.eqType) && propertyName.equals(equipment.propertyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, eqType, propertyName, propertyPoints);
    }
}
