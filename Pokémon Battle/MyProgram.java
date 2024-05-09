import java.util.*; //made with far too much ambition and an AI code assistant because i am incompetent and forget basic keywords sometimes
import java.text.*; //oh and don't mind everything being static, i couldn't find it within myself to care enough about fixing the bugs in a better way
public class MyProgram {
    static int teamSize;
    static int max=80, min = 10; 
    static boolean win;
    static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        DecimalFormat d = new DecimalFormat("##.##"); //this neat trick allows me to not have essay-length stat numbers
        System.out.print("How many Pokémon would you like to have? (Numerical) ");//this is the first problem: i should have made it static 
        teamSize = s.nextInt();
        s.nextLine();
        while(teamSize>6 || teamSize <= 0){
            System.out.print("Invalid team size. Input a number equal to or lower than 6, but greater than 0. ");
            teamSize = s.nextInt(); s.nextLine();} // eat the newline here just in case
        ArrayList<Pokémon> pokémonTeam = new ArrayList<>(); //array for the team because dynamic objects are hard
        for (int i = 1; i <= teamSize; i++) { //this is probably what most people did, and i considered adding a Pokédex of sorts, but that's way too much work
            System.out.print("Input name of Pokémon " + i + ": "); //correction, i will be adding a Pokédex, but not for the player
            String pokémonName = s.nextLine();
            System.out.print("Input type of Pokémon " + i + ": ");
            String pokémonType = s.nextLine();
            System.out.print("Input base HP of Pokémon " + i + ": ");
            double baseHP = s.nextDouble(); s.nextLine(); // eat the newline
            System.out.print("Input base attack of Pokémon " + i + ": ");
            double baseATK = s.nextDouble(); s.nextLine(); // eat the newline
            System.out.print("Input base defense of Pokémon " + i + ": ");
            double baseDEF = s.nextDouble(); s.nextLine(); // eat the newline
            System.out.print("Input level of Pokémon " + i + ": (Note: the enemy level ranges from 10 to 80) ");
            int level = s.nextInt(); //i'm sure the theoretical player has internet access, they can go on bulbapedia and get all these themselves
            s.nextLine(); // eat the newline one last time
            Pokémon currentPokémon = new Pokémon(pokémonName, pokémonType, baseHP, baseATK, baseDEF, level); 
            pokémonTeam.add(currentPokémon);} //and then we move on and hope for the best!
        System.out.println("Your team:");
        for (Pokémon currentPokémon : pokémonTeam) {
            System.out.println("********************");
            System.out.println("Pokémon: " + currentPokémon.PokémonName());
            System.out.println("Type: " + currentPokémon.PokémonType());
            System.out.println("Level: " + currentPokémon.statLevel());
            System.out.println("HP: " + d.format(currentPokémon.statHP()));
            System.out.println("ATK: " + d.format(currentPokémon.statATK()));
            System.out.println("DEF: " + d.format(currentPokémon.statDEF()));
            System.out.println("********************");} //just a little "now print all of them" loop
        System.out.println("The enemy team:");
        ArrayList<Pokémon> enemyTeam = enemyTeam();
        for (Pokémon currentPokémon : enemyTeam) {
            System.out.println("********************");
            System.out.println("Pokémon: " + currentPokémon.PokémonName());
            System.out.println("Type: " + currentPokémon.PokémonType());
            System.out.println("Level: " + currentPokémon.statLevel());
            System.out.println("HP: " + d.format(currentPokémon.statHP()));
            System.out.println("ATK: " + d.format(currentPokémon.statATK()));
            System.out.println("DEF: " + d.format(currentPokémon.statDEF()));
            System.out.println("********************");} //just a little "now print all of them" loop, again, this time for enemy
        System.out.print("Choose a Pokémon for the battle (1-" + teamSize + ") ");
        int userChoice = s.nextInt();s.nextLine();
        while (userChoice < 1 || userChoice > teamSize) {
            System.out.print("Invalid choice. Choose a Pokémon within team size limit (1-" + teamSize + ") ");
            userChoice = s.nextInt();s.nextLine();}
        Pokémon userPokémon = pokémonTeam.get(userChoice - 1);
        System.out.println("You chose " + userPokémon.PokémonName() + "!");
        Pokémon enemyPokémon = selectRandomPokémon(enemyTeam);
        System.out.println("The enemy chose " + enemyPokémon.PokémonName() + "!");
        System.out.println("Battle begin!");
        while(true){//calling it now - this loop alone will be 50+ lines
            System.out.println(userPokémon.PokémonName()+"'s HP: "+d.format(userPokémon.currentHP()));
            System.out.println("Enemy "+enemyPokémon.PokémonName()+"'s HP: "+d.format(enemyPokémon.currentHP()));
            String action = getAction();
            if(action.equalsIgnoreCase("Attack")){
                double damage = calcDamageDone(userPokémon, enemyPokémon);
                System.out.println(userPokémon.PokémonName()+" dealt "+d.format(damage)+" damage to "+enemyPokémon.PokémonName()+"!");
                enemyPokémon.damage(damage);
                if(enemyPokémon.currentHP() == 0){
                    enemyPokémon.faint();
                    System.out.println("Enemy "+enemyPokémon.PokémonName()+" fainted!");
                    if(allFainted(enemyTeam)){win = true;break;}
                    enemyPokémon = selectRandomPokémon(enemyTeam);
                    System.out.println("The enemy chose "+enemyPokémon.PokémonName()+"!");}}
            if(action.equalsIgnoreCase("Switch")){
                System.out.println("Select Pokémon to swap to (1-"+teamSize+") ");
                userChoice = s.nextInt();
                userPokémon = swapPokémon(pokémonTeam, userChoice);
                System.out.println("You chose "+userPokémon.PokémonName()+"!");}
            String enemyChoice = enemyActionChoice(enemyPokémon);
            if(enemyChoice.equalsIgnoreCase("Attack")){
                double enemyDamage = calcDamageDone(enemyPokémon, userPokémon);
                System.out.println("Enemy "+enemyPokémon.PokémonName()+" dealt "+d.format(enemyDamage)+" damage to "+userPokémon.PokémonName()+"!");
                userPokémon.damage(enemyDamage);
                if(userPokémon.currentHP() == 0){
                    userPokémon.faint();
                    System.out.println("Your "+userPokémon.PokémonName()+" fainted!");
                    if(allFainted(pokémonTeam)){win = false;break;}
                    System.out.println("Select Pokémon to swap to (1-"+teamSize+") ");
                    userChoice = s.nextInt();
                    userPokémon = swapPokémon(pokémonTeam, userChoice);
                    System.out.println("You chose "+userPokémon.PokémonName()+"!");}}
            if(enemyChoice.equalsIgnoreCase("Switch")){
                System.out.println("The enemy withdrew "+enemyPokémon.PokémonName()+"!");
                enemyPokémon = selectRandomPokémon(enemyTeam);
                System.out.println("The enemy chose "+enemyPokémon.PokémonName()+"!");}}//i was right
        if(win == true){
            System.out.println("Victory!");
            s.nextLine();}
        if(win == false){
            System.out.println("Defeat!");
            s.nextLine();}
        s.close();}
        public static boolean allFainted(ArrayList<Pokémon> enemyTeam) {
            for (Pokémon enemy : enemyTeam){if(!enemy.isFainted()){return false;}}return true;}        
    public static String enemyActionChoice(Pokémon enemyPokémon){
        Random r = new Random();
        int tricoinFlip = r.nextInt(3) + 1;
        if(tricoinFlip == 1 && (enemyPokémon.currentHP() <= (enemyPokémon.statHP()/2))){return "Switch";}
        else{return "Attack";}}
    public static int getTeamSize(){
        return MyProgram.teamSize;}
        public static String getAction() {//okay, this method here brought me immense hatred 
            String action = "";
            while (!action.equalsIgnoreCase("attack") && !action.equalsIgnoreCase("switch")) {
                System.out.println("\"Attack\" in order to deal damage, or \"Switch\" to change to a different Pokémon!");
                action = s.nextLine();
                if (!action.equalsIgnoreCase("attack") && !action.equalsIgnoreCase("switch")){
                    System.out.println("Invalid action!");}}//because of this single undebuggable line. i can't fix it. i literally cannot make it shut up.
                return action;}        
    public static double calcDamageDone(Pokémon Attacker, Pokémon Defender){
        double damage = ((Attacker.statATK())-(Defender.statDEF()));
        if(damage >= 0){return damage;}
        else{return 0.0;}}
    public static Pokémon swapPokémon(ArrayList<Pokémon> pokémonTeam, int teamNumber){
        Pokémon Pokémon = pokémonTeam.get(teamNumber - 1);
        return Pokémon;}
    public static ArrayList<Pokémon> enemyTeam() { //this chunklet here is largely AI, due to being simple randomizer logic that i didn't want to manually write out
        ArrayList<Pokémon> enemyTeam = new ArrayList<>();
        ArrayList<Pokémon> pokédex = getPokédex();
        Random r = new Random();
        int size = getTeamSize();
        for(int i = 0; i < size; i++){
            int randomIndex = r.nextInt(pokédex.size());
            Pokémon thisPokémon = pokédex.get(randomIndex);
            enemyTeam.add(thisPokémon);}
        return enemyTeam;}
    public static Pokémon selectRandomPokémon(ArrayList<Pokémon> enemyTeam) {
        Random r = new Random();
        int randomIndex = r.nextInt(enemyTeam.size());
        Pokémon selected = enemyTeam.get(randomIndex);
        while(selected.isFainted() == true){
            randomIndex = r.nextInt(enemyTeam.size());
            selected = enemyTeam.get(randomIndex);}
        return selected;}
    public static ArrayList<Pokémon> getPokédex() { //this here is what one would call "the beginning of the end", and yes i reformatted all of my code
        Random r = new Random();
        ArrayList<Pokémon> pokédex = new ArrayList<>(); //it is currently 11:03AM on Dec 1st, i will put in another comment when i start regretting this decision
        Pokémon Rayquaza = new Pokémon("Rayquaza", "Dragon/Flying", 105, 150, 90, 80); 
        pokédex.add(Rayquaza); //it is 12:38PM, and i regret this - the past hour has been all debugging
        Pokémon Whirlipede = new Pokémon("Whirlipede", "Bug/Poison", 40, 55, 99, r.nextInt((max-min+1)+min)); 
        pokédex.add(Whirlipede);
        Pokémon Prinplup = new Pokémon("Prinplup", "Water", 64, 66, 68, r.nextInt((max-min+1)+min)); 
        pokédex.add(Prinplup);
        Pokémon Mightyena = new Pokémon("Mightyena", "Dark", 70, 90, 70, r.nextInt((max-min+1)+min)); 
        pokédex.add(Mightyena);
        Pokémon Zapdos = new Pokémon("Zapdos", "Electric/Flying", 90, 90, 85, 80); 
        pokédex.add(Zapdos);
        Pokémon Hippowdon = new Pokémon("Hippowdon", "Ground", 108, 112, 118, r.nextInt((max-min+1)+min)); 
        pokédex.add(Hippowdon);
        Pokémon Heatmor = new Pokémon("Heatmor", "Fire", 85, 97, 66, r.nextInt((max-min+1)+min)); 
        pokédex.add(Heatmor);
        Pokémon Togedemaru = new Pokémon("Togedemaru", "Electric/Steel", 65, 98, 63, r.nextInt((max-min+1)+min)); 
        pokédex.add(Togedemaru);
        Pokémon Duraludon = new Pokémon("Duraludon", "Dragon/Steel", 70, 95, 115, r.nextInt((max-min+1)+min)); 
        pokédex.add(Duraludon);
        Pokémon Klawf = new Pokémon("Klawf", "Rock", 70, 100, 115, r.nextInt((max-min+1)+min)); 
        pokédex.add(Klawf);
        Pokémon Ampharos = new Pokémon("Ampharos", "Electric", 90, 75, 75, r.nextInt((max-min+1)+min)); 
        pokédex.add(Ampharos);
        Pokémon Clamperl = new Pokémon("Clamperl", "Water", 35, 64, 85, r.nextInt((max-min+1)+min)); 
        pokédex.add(Clamperl);
        Pokémon Houndour = new Pokémon("Houndour", "Dark/Fire", 45, 60, 30, r.nextInt((max-min+1)+min)); 
        pokédex.add(Houndour);
        Pokémon Glalie = new Pokémon("Glalie", "Ice", 80, 80, 80, r.nextInt((max-min+1)+min)); 
        pokédex.add(Glalie);
        Pokémon Heracross = new Pokémon("Heracross", "Bug/Fighting", 80, 125, 75, r.nextInt((max-min+1)+min)); 
        pokédex.add(Heracross);
        Pokémon Elgyem = new Pokémon("Elgyem", "Psychic", 55, 55, 55, r.nextInt((max-min+1)+min)); 
        pokédex.add(Elgyem);
        Pokémon Greedent = new Pokémon("Greedent", "Normal", 120, 95, 95, r.nextInt((max-min+1)+min)); 
        pokédex.add(Greedent);
        Pokémon Clodsire = new Pokémon("Clodsire", "Poison/Ground", 130, 75, 60, r.nextInt((max-min+1)+min)); 
        pokédex.add(Clodsire);
        Pokémon Morgrem = new Pokémon("Morgrem", "Dark/Fairy", 65, 60, 45, r.nextInt((max-min+1)+min)); 
        pokédex.add(Morgrem);
        Pokémon Greavard = new Pokémon("Greavard", "Ghost", 50, 61, 60, r.nextInt((max-min+1)+min)); 
        pokédex.add(Greavard);
        Pokémon Ferroseed = new Pokémon("Ferroseed", "Grass/Steel", 44, 50, 91, r.nextInt((max-min+1)+min)); 
        pokédex.add(Ferroseed);
        Pokémon Dusknoir = new Pokémon("Dusknoir", "Ghost", 45, 100, 135, r.nextInt((max-min+1)+min)); 
        pokédex.add(Dusknoir);
        Pokémon Milotic = new Pokémon("Milotic", "Water", 95, 60, 79, r.nextInt((max-min+1)+min)); 
        pokédex.add(Milotic);
        Pokémon Petilil = new Pokémon("Petilil", "Grass", 45, 35, 50, r.nextInt((max-min+1)+min)); 
        pokédex.add(Petilil);
        Pokémon Sneasel = new Pokémon("Sneasel", "Dark/Ice", 55, 95, 55, r.nextInt((max-min+1)+min)); 
        pokédex.add(Sneasel);
        Pokémon Rhyhorn = new Pokémon("Rhyhorn", "Ground/Rock", 80, 85, 95, r.nextInt((max-min+1)+min)); 
        pokédex.add(Rhyhorn);
        Pokémon Hariyama = new Pokémon("Hariyama", "Fighting", 144, 120, 60, r.nextInt((max-min+1)+min)); 
        pokédex.add(Hariyama);
        Pokémon Virizion = new Pokémon("Virizion", "Grass/Fighting", 91, 90, 72, 80); 
        pokédex.add(Virizion);
        Pokémon Dartrix = new Pokémon("Dartrix", "Grass/Flying", 78, 75, 75, r.nextInt((max-min+1)+min)); 
        pokédex.add(Dartrix);
        Pokémon Tropius = new Pokémon("Tropius", "Grass/Flying", 99, 68, 83, r.nextInt((max-min+1)+min)); 
        pokédex.add(Tropius); //i regret this a lot
        return pokédex;} //never again
}
class Pokémon{ //i am actually somewhat proud of how well i remembered how to write these
    private String pokémonName;
    private String type;
    private double baseHP;
    private double baseATK;
    private double baseDEF;
    private int level;
    private double currentHP;
    private boolean fainted;
    public Pokémon(String pokémonName, String type, double baseHP, double baseATK, double baseDEF, int level){
        this.pokémonName = pokémonName;
        this.type = type;
        this.baseHP = baseHP;
        this.baseATK = baseATK;
        this.baseDEF = baseDEF;
        this.level = level;
        this.currentHP = this.statHP();
        this.fainted = false;}
    public String PokémonName(){
        return pokémonName;}
    public String PokémonType(){
        return type;}
    public double currentHP(){
        return currentHP;}
    public double statHP(){
        double hp = levelHPCalc(level, baseHP); //might as well do the math for the leveled stats here rather than wait or do it in main
        return hp;}
    public double statATK(){
        double atk = levelATKCalc(level, baseATK); //rinse and repeat for each stat
        return atk;}
    public double statDEF(){
        double def = levelDEFCalc(level, baseDEF);
        return def;}
    public int statLevel(){
        return level;} //now, is this ideal? probably not, but i don't really care, it works
    public static double levelHPCalc(int level, double baseHP){ //i pretty much ripped these 3 from irl friend 
        double number = 11;
        for(int i=1;i<level;i++){
            number = number+((baseHP*0.02)+1);}
        return number;}
    public static double levelATKCalc(int level, double baseATK){ //but to be fair there's only so many ways to do it
        double number = 5;
        for(int i = 1;i<level;i++){
           number = number+baseATK*0.02;}
        return number;}
    public static double levelDEFCalc(int level, double baseDEF){ //and i didn't just directly copy-paste
        double number = 5;
        for(int i = 1;i<level;i++){
            number = number+baseDEF*0.02;}
        return number;}
    public void damage(double damage) {
        currentHP -= damage;
        if (currentHP < 0){currentHP = 0;}}
    public void faint(){
        fainted = true;}
    public boolean isFainted(){
        return(fainted);}
}
