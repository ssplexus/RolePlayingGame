package Game.Equipment;

import java.util.Objects;

/**
 *  Базовый класс снаряжения
 */
public class Equipment
{
    private final String name; // наименование
    private final String eqType; // тип (оружие, броня, медикамент)
    private final String propertyName; // название свойства снаряжения
    private final int propertyPoints;  // очки свойства которое даёт снаряжение

    // Конструктор снаряжения
    protected Equipment(String name, String eqType, String propertyName, int propertyPoints)
    {
        this.name = name;
        this.eqType = eqType;
        this.propertyName = propertyName;
        this.propertyPoints = propertyPoints;
    }

    /** Получить наименование снаряжения
     *
     * @return наименование снаряжения
     */
    public String getName() {
        return name;
    }

    /** Получить тип снаряжения
     *
     * @return
     */
    public String getEqType() {
        return eqType;
    }

    /** Получить очки снаряжения
     *
     * @return
     */
    public int getPropertyPoints() {
        return propertyPoints;
    }

    /** Получить строковое представление объекта снаряжения
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString()
    {
        return new StringBuilder().append(name + " :").
                append("[type = " + eqType  + "]").
                append("[" + propertyName + " = " + propertyPoints + "]").toString();
    }

    /** Метод определения равенства
     *
     * @param o - с чем сравнивается текущий объект
     * @return - результат сравнения
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return propertyPoints == equipment.propertyPoints && name.equals(equipment.name) && eqType.equals(equipment.eqType) && propertyName.equals(equipment.propertyName);
    }

    /** Получить хэш-код объекта
     *
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, eqType, propertyName, propertyPoints);
    }
}
