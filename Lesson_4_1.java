import java.util.Random;

public class Lesson_4_1 {
    public static int bossHealth = 600;
    public static int bossDamage = 50;
    public static String bossDefence;

    public static int[] heroesHealth = {270, 260, 250};
    public static int medicHealth = 255;

    public static int[] heroesDamage = {20, 15, 10};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic"};

    public static int roundNumber;

    public static void main(String[] args) {
        printStatistics();

        while (!isGameOver()) {
            playRound();
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int heroHealth : heroesHealth) {
            if (heroHealth > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead && medicHealth <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossAttack();
        heroesAttack();
        medicHealing();
        printStatistics();
        //RandomHealing();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void bossAttack() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                heroesHealth[i] = Math.max(0, heroesHealth[i] - bossDamage);
            }
        }
        if (medicHealth > 0) {
            medicHealth = Math.max(0, medicHealth - bossDamage);
        }
    }

    public static void heroesAttack() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType[i].equals(bossDefence)) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    damage *= coeff;
                    System.out.println("Critical damage: " + damage);
                }
                bossHealth = Math.max(0, bossHealth - damage);
            }
        }
    }

    public static void medicHealing() {
        if (medicHealth > 0) {
            Random random = new Random();
            int randomHeroIndex = random.nextInt(heroesHealth.length);
            if (heroesHealth[randomHeroIndex] < 100) {
                heroesHealth[randomHeroIndex] += 20;
                System.out.println("Medic healed " + heroesAttackType[randomHeroIndex] + " by 20 health points. New health: " + heroesHealth[randomHeroIndex]);
            }
        }
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ---------------");
        System.out.println("BOSS health: " + bossHealth + " damage: " + bossDamage
                + " defence: " + (bossDefence == null ? "No defence" : bossDefence));
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] +
                    " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);
        }
        System.out.println("Medic health: " + medicHealth);
    }
}


