/**
 * Classe de base.
 * - Le nom (name) est une propriété publique et immuable (val).
 * - L'âge (age) est une propriété mutable (var) mais protégée (protected),
 *   ce qui signifie que seules les classes filles (Human, Robot) peuvent la modifier.
 */
class Person(val name: String) {
  protected var age: Int = 0

  override def toString: String = s"$name a $age an(s)"
}

/**
 * La classe Human hérite de Person.
 * Le nom est passé au constructeur parent.
 * L'âge ne peut que croître.
 */
class Human(name: String) extends Person(name) {
  def feteAnniversaire(): Unit = {
    this.age += 1
  }
}

/**
 * La classe Robot hérite de Person.
 * - Peut être créé avec un âge initial.
 * - Son âge peut croître ou décroître.
 */
class Robot(name: String, initialAge: Int) extends Person(name) {
  // On assigne l'âge initial au champ 'age' hérité de Person.
  this.age = initialAge

  def effectuerMaintenance(): Unit = {
    if (this.age > 0) {
      this.age -= 1 // L'âge peut décroître.
    }
  }

  def travailler(): Unit = {
    this.age += 1 // L'âge peut croître.
  }
}

/**
 * Un trait concret qui ajoute des capacités sociales.
 * Il définit une propriété 'bestFriend' qui peut être absente.
 * On utilise Option[Person] pour modéliser cela de manière sûre.
 */
trait Social {
  var bestFriend: Option[Person] = None
}

/**
 * Un SocialRobot est un Robot qui a des capacités sociales (le trait Social).
 */
class SocialRobot(name: String, initialAge: Int) extends Robot(name, initialAge) with Social {

  /**
   * Redéfinition de toString pour inclure le statut du meilleur ami.
   */
  override def toString: String = {
    // On récupère la description de base (nom et âge) de la classe Person
    val descriptionDeBase = super.toString

    // On gère l'information sur le meilleur ami.
    // L'enchaînement .map(...).getOrElse(...) est une façon très concise et idiomatique
    // de faire la même chose que le 'match' commenté ci-dessous.
    val descriptionAmi = this.bestFriend
      .map(friend => s"son meilleur ami est ${friend.name}")
      .getOrElse("il n'a pas de meilleur ami")

    /* Version alternative avec un 'match' (plus verbeux mais parfois plus lisible) :
    val descriptionAmi = this.bestFriend match {
      case Some(friend) => s"son meilleur ami est ${friend.name}"
      case None         => "il n'a pas de meilleur ami"
    }
    */

    // On combine les deux descriptions
    s"$descriptionDeBase, et $descriptionAmi."
  }
}

