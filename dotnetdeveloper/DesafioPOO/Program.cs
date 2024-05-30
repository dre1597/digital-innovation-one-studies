using DesafioPOO;

Console.WriteLine("Nokia: ");
var nokia = new Nokia("123", "1", 6, 128);

nokia.Call();
nokia.InstallApp("WhatsApp");
nokia.Answer();

Console.WriteLine("Iphone: ");
var iphone = new Iphone("321", "7", 4, 64);

iphone.Call();
iphone.InstallApp("Facebook");
iphone.Answer();
