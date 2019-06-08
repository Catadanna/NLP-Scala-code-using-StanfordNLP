object DataEn {
  val d:String = "Here is the hardware I have. Galaxy Gear S3 - Smartwatch OnePlus 7 Pro - Phone Yunmai Color - Scale.   The scale works on an old phone with an older version of Samsung Health. There are no problems with the scale app the scale is found every time I go to the Yunmai app and shows it right away. Bluetooth is there and it is linked. Within the Samsung Health App it sees the Yunmai scale device when I go in and connect the device. Where it used to show it in the list of accessories I do have to let it scan for it instead (I assume due to a software upgrade since my Samsung Health app doesn't even show scales anymore).   I can get it to work one time only by doing the following steps. 1) Deleting the Yunmai Color scale from bluetooth. 2) Unconnecting the Yunmai Color scale from Samsung health. 3) Force closing the app. 4) Pairing the Bluetooth on the phone to the Yunmai scale. 5) Reconnecting the Yunmai scale to Samsung Health by a scan. 6) Then going to Weight and Record. However the very next time I go to do it (it could be two days or one minute, reboots of phone don't matter) I have to do all these steps all over again to get an \"automatic\" weight. This is unacceptable because the entire reason I got the Yunmai scale was for it to work. This has been happening ever since Samsung Health has last updated. How can I get Samsung health to reliably see my \"supported\" scale when the Yunmai program sees it fine every time? I already asked Yunmai but they state since the phone see it thorugh their app I have to ask here."

  // Create the first representation of the document :
  val listEntities:List[Entity] = List(
    Entity(1, 1, 1, 29, 43), //Galaxy Gear S3
    Entity(2, 2, 1, 57, 70), //OnePlus 7 Pro
    Entity(3, 3, 1, 79, 91), //Yunmai Color
    Entity(4, 4, 1, 160, 174), //Samsung Health
    Entity(5, 3, 1, 259, 266), // Yunmai
    Entity(6, 4, 1, 343, 361), // Samsung Health App
    Entity(7, 3, 1, 374, 380), //Yunmai
    Entity(8, 4, 1, 568, 586), //Samsung Health app
    Entity(9, 3, 1, 705, 717), //Yunmai Color
    Entity(10, 3, 1, 760, 772) //Yunmai Color
  )

}
