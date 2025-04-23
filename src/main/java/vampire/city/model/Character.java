package vampire.city.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="character")
@Table(name="character")
public class Character {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column
    private String name;
    @ManyToOne
    @JoinColumn(name="clan_id")
    private Clan clan;
    @Column
    private int generation;
    @Column
    private String sire;
    @Column
    private String nature;
    @Column
    private String demeanor;
    @Column
    private String concept;

    // Atributos
    @Column
    private int strength;
    @Column
    private int dexterity;
    @Column
    private int stamina;
    @Column
    private int charisma;
    @Column
    private int manipulation;
    @Column
    private int appearance;
    @Column
    private int perception;
    @Column
    private int intelligence;
    @Column
    private int wits;

    // Talentos
    @Column
    private int alertness;
    @Column
    private int athletics;
    @Column
    private int awareness;
    @Column
    private int brawl;
    @Column
    private int empathy;
    @Column
    private int expression;
    @Column
    private int intimidation;
    @Column
    private int leadership;
    @Column
    private int streetwise;
    @Column
    private int subterfuge;

    // Habilidades
    @Column
    private int animal_kin;
    @Column
    private int archery;
    @Column
    private int crafts;
    @Column
    private int etiquette;
    @Column
    private int legerdemain;
    @Column
    private int melee;
    @Column
    private int performance;
    @Column
    private int ride;
    @Column
    private int stealth;
    @Column
    private int survival;

    //conhecimentos
    @Column
    private int academics;
    @Column
    private int enigmas;
    @Column
    private int heart_wisdom;
    @Column
    private int investigation;
    @Column
    private int law;
    @Column
    private int medicine;
    @Column
    private int occult;
    @Column
    private int politics;
    @Column
    private int seneschal;
    @Column
    private int theology;

    // Disciplinas (simplificadas)
    @Column(name = "clan_discipline1")
    private int clanDiscipline1;
    @Column(name = "clan_discipline2")
    private int clanDiscipline2;
    @Column(name = "clan_discipline3")
    private int clanDiscipline3;
    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Discipline> disciplinas = new ArrayList<>();

    // Virtudes
    @Column
    private int conscience;
    @Column
    private int courage;
    @Column
    private int self_control;

    // Outros
    @ManyToOne
    @JoinColumn(name = "road_id")
    private Road road;
    @Column
    private int road_value;
    @Column
    private int willpower;
    @Column
    private int bloodpool;
    @Column
    private int experience;

    public Character() {
    }

    public Character(User user, String name, Clan clan, int generation, String sire, String nature, String demeanor, String concept, int strength, int dexterity, int stamina, int charisma, int manipulation, int appearance, int perception, int intelligence, int wits, int alertness, int athletics, int awareness, int brawl, int empathy, int expression, int intimidation, int leadership, int streetwise, int subterfuge, int animal_kin, int archery, int crafts, int etiquette, int legerdemain, int melee, int performance, int ride, int stealth, int survival, int academics, int enigmas, int heart_wisdom, int investigation, int law, int medicine, int occult, int politics, int seneschal, int theology, int clanDiscipline1, int clanDiscipline2, int clanDiscipline3, int conscience, int courage, int self_control, Road road, int road_value, int willpower, int bloodpool) {
        this.user = user;
        this.name = name;
        this.clan = clan;
        this.generation = generation;
        this.sire = sire;
        this.nature = nature;
        this.demeanor = demeanor;
        this.concept = concept;
        this.strength = strength;
        this.dexterity = dexterity;
        this.stamina = stamina;
        this.charisma = charisma;
        this.manipulation = manipulation;
        this.appearance = appearance;
        this.perception = perception;
        this.intelligence = intelligence;
        this.wits = wits;
        this.alertness = alertness;
        this.athletics = athletics;
        this.awareness = awareness;
        this.brawl = brawl;
        this.empathy = empathy;
        this.expression = expression;
        this.intimidation = intimidation;
        this.leadership = leadership;
        this.streetwise = streetwise;
        this.subterfuge = subterfuge;
        this.animal_kin = animal_kin;
        this.archery = archery;
        this.crafts = crafts;
        this.etiquette = etiquette;
        this.legerdemain = legerdemain;
        this.melee = melee;
        this.performance = performance;
        this.ride = ride;
        this.stealth = stealth;
        this.survival = survival;
        this.academics = academics;
        this.enigmas = enigmas;
        this.heart_wisdom = heart_wisdom;
        this.investigation = investigation;
        this.law = law;
        this.medicine = medicine;
        this.occult = occult;
        this.politics = politics;
        this.seneschal = seneschal;
        this.theology = theology;
        this.clanDiscipline1 = clanDiscipline1;
        this.clanDiscipline2 = clanDiscipline2;
        this.clanDiscipline3 = clanDiscipline3;
        this.conscience = conscience;
        this.courage = courage;
        this.self_control = self_control;
        this.road = road;
        this.road_value = road_value;
        this.willpower = willpower;
        this.bloodpool = bloodpool;
    }

    public int getId() {
        return Id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
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

    public List<Discipline> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Discipline> disciplinas) {
        this.disciplinas = disciplinas;
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

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
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
}
