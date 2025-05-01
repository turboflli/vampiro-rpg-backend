package vampire.city.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CharacterDTO {
    private Integer id;
    
    private String name;
    private int clanId;
    private int generation;
    private String sire;
    private String nature;
    private String demeanor;
    private String concept;

    // Atributos
    private int strength;
    private int dexterity;
    private int stamina;
    private int charisma;
    private int manipulation;
    private int appearance;
    private int perception;
    private int intelligence;
    private int wits;

    // Talentos
    private int alertness;
    private int athletics;
    private int awareness;
    private int brawl;
    private int empathy;
    private int expression;
    private int intimidation;
    private int leadership;
    private int streetwise;
    private int subterfuge;

    // Habilidades
    
    private int animal_kin;
    private int archery;
    private int crafts;
    private int etiquette;
    private int legerdemain;
    private int melee;
    private int performance;
    private int ride;
    private int stealth;
    private int survival;

    //conhecimentos
    
    private int academics;
    private int enigmas;
    private int heart_wisdom;
    private int investigation;
    private int law;
    private int medicine;
    private int occult;
    private int politics;
    private int seneschal;
    private int theology;

    // Disciplinas (simplificadas)
    private int clanDiscipline1;
    private int clanDiscipline2;
    private int clanDiscipline3;
    private List<DisciplineDTO> disciplines = new ArrayList<>();

    // Virtudes
    private int conscience;
    private int courage;
    private int self_control;

    // Outros
    private int roadId;
    private int road_value;
    private int willpower;
    private int bloodpool;
    private int experience;

    public void setId(Integer id) {
        id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClanId() {
        return clanId;
    }

    public void setClanId(int clanId) {
        this.clanId = clanId;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getSire() {
        return sire;
    }

    public void setSire(String sire) {
        this.sire = sire;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getDemeanor() {
        return demeanor;
    }

    public void setDemeanor(String demeanor) {
        this.demeanor = demeanor;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getManipulation() {
        return manipulation;
    }

    public void setManipulation(int manipulation) {
        this.manipulation = manipulation;
    }

    public int getAppearance() {
        return appearance;
    }

    public void setAppearance(int appearance) {
        this.appearance = appearance;
    }

    public int getPerception() {
        return perception;
    }

    public void setPerception(int perception) {
        this.perception = perception;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWits() {
        return wits;
    }

    public void setWits(int wits) {
        this.wits = wits;
    }

    public int getAlertness() {
        return alertness;
    }

    public void setAlertness(int alertness) {
        this.alertness = alertness;
    }

    public int getAthletics() {
        return athletics;
    }

    public void setAthletics(int athletics) {
        this.athletics = athletics;
    }

    public int getAwareness() {
        return awareness;
    }

    public void setAwareness(int awareness) {
        this.awareness = awareness;
    }

    public int getBrawl() {
        return brawl;
    }

    public void setBrawl(int brawl) {
        this.brawl = brawl;
    }

    public int getEmpathy() {
        return empathy;
    }

    public void setEmpathy(int empathy) {
        this.empathy = empathy;
    }

    public int getExpression() {
        return expression;
    }

    public void setExpression(int expression) {
        this.expression = expression;
    }

    public int getIntimidation() {
        return intimidation;
    }

    public void setIntimidation(int intimidation) {
        this.intimidation = intimidation;
    }

    public int getLeadership() {
        return leadership;
    }

    public void setLeadership(int leadership) {
        this.leadership = leadership;
    }

    public int getStreetwise() {
        return streetwise;
    }

    public void setStreetwise(int streetwise) {
        this.streetwise = streetwise;
    }

    public int getSubterfuge() {
        return subterfuge;
    }

    public void setSubterfuge(int subterfuge) {
        this.subterfuge = subterfuge;
    }

    public int getAnimal_kin() {
        return animal_kin;
    }

    public void setAnimal_kin(int animal_kin) {
        this.animal_kin = animal_kin;
    }

    public int getArchery() {
        return archery;
    }

    public void setArchery(int archery) {
        this.archery = archery;
    }

    public int getCrafts() {
        return crafts;
    }

    public void setCrafts(int crafts) {
        this.crafts = crafts;
    }

    public int getEtiquette() {
        return etiquette;
    }

    public void setEtiquette(int etiquette) {
        this.etiquette = etiquette;
    }

    public int getLegerdemain() {
        return legerdemain;
    }

    public void setLegerdemain(int legerdemain) {
        this.legerdemain = legerdemain;
    }

    public int getMelee() {
        return melee;
    }

    public void setMelee(int melee) {
        this.melee = melee;
    }

    public int getPerformance() {
        return performance;
    }

    public void setPerformance(int performance) {
        this.performance = performance;
    }

    public int getRide() {
        return ride;
    }

    public void setRide(int ride) {
        this.ride = ride;
    }

    public int getStealth() {
        return stealth;
    }

    public void setStealth(int stealth) {
        this.stealth = stealth;
    }

    public int getSurvival() {
        return survival;
    }

    public void setSurvival(int survival) {
        this.survival = survival;
    }

    public int getAcademics() {
        return academics;
    }

    public void setAcademics(int academics) {
        this.academics = academics;
    }

    public int getEnigmas() {
        return enigmas;
    }

    public void setEnigmas(int enigmas) {
        this.enigmas = enigmas;
    }

    public int getHeart_wisdom() {
        return heart_wisdom;
    }

    public void setHeart_wisdom(int heart_wisdom) {
        this.heart_wisdom = heart_wisdom;
    }

    public int getInvestigation() {
        return investigation;
    }

    public void setInvestigation(int investigation) {
        this.investigation = investigation;
    }

    public int getLaw() {
        return law;
    }

    public void setLaw(int law) {
        this.law = law;
    }

    public int getMedicine() {
        return medicine;
    }

    public void setMedicine(int medicine) {
        this.medicine = medicine;
    }

    public int getOccult() {
        return occult;
    }

    public void setOccult(int occult) {
        this.occult = occult;
    }

    public int getPolitics() {
        return politics;
    }

    public void setPolitics(int politics) {
        this.politics = politics;
    }

    public int getSeneschal() {
        return seneschal;
    }

    public void setSeneschal(int seneschal) {
        this.seneschal = seneschal;
    }

    public int getTheology() {
        return theology;
    }

    public void setTheology(int theology) {
        this.theology = theology;
    }

    public int getClanDiscipline1() {
        return clanDiscipline1;
    }

    public void setClanDiscipline1(int clanDiscipline1) {
        this.clanDiscipline1 = clanDiscipline1;
    }

    public int getClanDiscipline2() {
        return clanDiscipline2;
    }

    public void setClanDiscipline2(int clanDiscipline2) {
        this.clanDiscipline2 = clanDiscipline2;
    }

    public int getClanDiscipline3() {
        return clanDiscipline3;
    }

    public void setClanDiscipline3(int clanDiscipline3) {
        this.clanDiscipline3 = clanDiscipline3;
    }

    public List<DisciplineDTO> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<DisciplineDTO> disciplines) {
        this.disciplines = disciplines;
    }

    public int getConscience() {
        return conscience;
    }

    public void setConscience(int conscience) {
        this.conscience = conscience;
    }

    public int getCourage() {
        return courage;
    }

    public void setCourage(int courage) {
        this.courage = courage;
    }

    public int getSelf_control() {
        return self_control;
    }

    public void setSelf_control(int self_control) {
        this.self_control = self_control;
    }

    public int getRoadId() {
        return roadId;
    }

    public void setRoadId(int roadId) {
        this.roadId = roadId;
    }

    public int getRoad_value() {
        return road_value;
    }

    public void setRoad_value(int road_value) {
        this.road_value = road_value;
    }

    public int getWillpower() {
        return willpower;
    }

    public void setWillpower(int willpower) {
        this.willpower = willpower;
    }

    public int getBloodpool() {
        return bloodpool;
    }

    public void setBloodpool(int bloodpool) {
        this.bloodpool = bloodpool;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public CharacterDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CharacterDTO that = (CharacterDTO) o;
        return clanId == that.clanId && generation == that.generation && strength == that.strength && dexterity == that.dexterity && stamina == that.stamina && charisma == that.charisma && manipulation == that.manipulation && appearance == that.appearance && perception == that.perception && intelligence == that.intelligence && wits == that.wits && alertness == that.alertness && athletics == that.athletics && awareness == that.awareness && brawl == that.brawl && empathy == that.empathy && expression == that.expression && intimidation == that.intimidation && leadership == that.leadership && streetwise == that.streetwise && subterfuge == that.subterfuge && animal_kin == that.animal_kin && archery == that.archery && crafts == that.crafts && etiquette == that.etiquette && legerdemain == that.legerdemain && melee == that.melee && performance == that.performance && ride == that.ride && stealth == that.stealth && survival == that.survival && academics == that.academics && enigmas == that.enigmas && heart_wisdom == that.heart_wisdom && investigation == that.investigation && law == that.law && medicine == that.medicine && occult == that.occult && politics == that.politics && seneschal == that.seneschal && theology == that.theology && clanDiscipline1 == that.clanDiscipline1 && clanDiscipline2 == that.clanDiscipline2 && clanDiscipline3 == that.clanDiscipline3 && conscience == that.conscience && courage == that.courage && self_control == that.self_control && roadId == that.roadId && road_value == that.road_value && willpower == that.willpower && bloodpool == that.bloodpool && experience == that.experience && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(sire, that.sire) && Objects.equals(nature, that.nature) && Objects.equals(demeanor, that.demeanor) && Objects.equals(concept, that.concept) && Objects.equals(disciplines, that.disciplines);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + clanId;
        result = 31 * result + generation;
        result = 31 * result + Objects.hashCode(sire);
        result = 31 * result + Objects.hashCode(nature);
        result = 31 * result + Objects.hashCode(demeanor);
        result = 31 * result + Objects.hashCode(concept);
        result = 31 * result + strength;
        result = 31 * result + dexterity;
        result = 31 * result + stamina;
        result = 31 * result + charisma;
        result = 31 * result + manipulation;
        result = 31 * result + appearance;
        result = 31 * result + perception;
        result = 31 * result + intelligence;
        result = 31 * result + wits;
        result = 31 * result + alertness;
        result = 31 * result + athletics;
        result = 31 * result + awareness;
        result = 31 * result + brawl;
        result = 31 * result + empathy;
        result = 31 * result + expression;
        result = 31 * result + intimidation;
        result = 31 * result + leadership;
        result = 31 * result + streetwise;
        result = 31 * result + subterfuge;
        result = 31 * result + animal_kin;
        result = 31 * result + archery;
        result = 31 * result + crafts;
        result = 31 * result + etiquette;
        result = 31 * result + legerdemain;
        result = 31 * result + melee;
        result = 31 * result + performance;
        result = 31 * result + ride;
        result = 31 * result + stealth;
        result = 31 * result + survival;
        result = 31 * result + academics;
        result = 31 * result + enigmas;
        result = 31 * result + heart_wisdom;
        result = 31 * result + investigation;
        result = 31 * result + law;
        result = 31 * result + medicine;
        result = 31 * result + occult;
        result = 31 * result + politics;
        result = 31 * result + seneschal;
        result = 31 * result + theology;
        result = 31 * result + clanDiscipline1;
        result = 31 * result + clanDiscipline2;
        result = 31 * result + clanDiscipline3;
        result = 31 * result + Objects.hashCode(disciplines);
        result = 31 * result + conscience;
        result = 31 * result + courage;
        result = 31 * result + self_control;
        result = 31 * result + roadId;
        result = 31 * result + road_value;
        result = 31 * result + willpower;
        result = 31 * result + bloodpool;
        result = 31 * result + experience;
        return result;
    }
}
