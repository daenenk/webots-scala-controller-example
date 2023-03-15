import com.cyberbotics.webots.controller.Robot;
import com.cyberbotics.webots.controller.Motor;

object EPuckGoForward {

  def main(args: Array[String]): Unit = {

    val robot = new Robot()
    val timeStep = Math.round(robot.getBasicTimeStep()).toInt

    val leftMotor = robot.getMotor("left wheel motor")
    val rightMotor = robot.getMotor("right wheel motor")
    
    leftMotor.setPosition(Double.PositiveInfinity)
    rightMotor.setPosition(Double.PositiveInfinity)
    
    val MAX_SPEED = 6.28
    leftMotor.setVelocity(0.1 * MAX_SPEED)
    rightMotor.setVelocity(0.1 * MAX_SPEED)
    
    while (robot.step(timeStep) != -1) {
    }
  }
}
