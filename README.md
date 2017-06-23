# Nerdesiniz?

Nerdesiniz uygulaması bir çok kullanıcının bir sanal odada buluşup, konumlarını gerçek zamanlı olarak birbirleriyle paylaşabilecekleri bir uygulamadır.

Bu proje [bilgetech](http://bilgetech.com.tr) Android takımına işe alım sürecinde kullanılacak challenge olarak oluşturulmuştur.

Bu repo'yu fork'layıp aşağıdaki spesifikasyonları da gözeterek Nerdesiniz uygulamasını geliştirmeniz beklenmektedir.

### Ekranlar

Temelde uygulamada iki adet ekran bulunmaktadır: Giriş Ekranı ve Harita Ekranı. Ekranları bu tasarımları göz önünde bulundururarak yapabilirsiniz ama önemli olan birebir bu tasarımlara benzemesi değil iyi ve temiz yazılmış olması, iyi çalışması ve güzel bi kullanıcı deneyimi sunmasıdır.

![giris ekrani](https://user-images.githubusercontent.com/4990386/27486868-a5f3d93e-583a-11e7-808d-4621de7cc096.png)
![harita ekrani](https://user-images.githubusercontent.com/4990386/27486869-a6122074-583a-11e7-9553-e47d1578071e.png)


#### Giriş Ekranı

Bu ekran uygulama launch edildiğinde ilk açılacak ekrandır. İsim ve oda numarası girilir ve renk seçilir. Giriş Yap butonuna basıldığında kullanıcı harita ekranına geçer.

Giriş ekranındaki arka plan rengi ve button rengi kullanıcının seçtiği renk ile değişir.

Renk seçimleri sırasında projede material renk paleti kullanılmıştır. Tasarımla implementasyon arasında küçük değişiklikler  olabilir.

#### Harita Ekranı

Bu ekranda seçilmiş olan oda canlı olarak gözlenir ve kullanıcının konumu yine bu odaya canlı olarak gönderilir.

Marker görselleri olarak Google'ın sağladığı marker'ların çeşitli renkleri kullanılabilir. Herhangi bir kullanıcının konumu değiştirildiğinde bu değişim event'i gözlenmeli ve o marker hareket ettirilmelidir.

Bu ekrandaki tüm veri alma ve gönderme işleri realtime olarak Firebase üzerinden yapılır.

### Firebase Database

Firebase dependency'si projeye eklenmiştir.

Reference: `https://bilgetech-challenge.firebaseio.com/`

![screen shot 2017-06-23 at 5 46 51 pm](https://user-images.githubusercontent.com/4990386/27487294-fd8a5f00-583b-11e7-8ee5-3381f6e685a5.png)

### LocationAwareActivity

Bu class, konum güncellemeleri alma, konum izni yönetimi ve benzeri konum servisleri ile ilgili tüm işlerden sorumludur. Bu konumla ilgili tüm fonksiyonları base activity mantığıyla ile bu sınıf içerisinde yazarsak, başka bir aktivitede de konum gerektiğinde kod kopyala/yapıştır yapılmasına gerek kalmadan bu class extend edilerek yazılmış olur.

**İpucu**: Runtime permissions, location servislerinin enable olup olmaması, vs gibi durumlar bu activity tarafından kontrol edilmeli ve ilgili aksiyon default olarak alınabilmelidir.

**İpucu**: Bu sınıf child class'larına `getLocationUpdates()` adlı bir fonksiyon sunarak, child class'ların kolay bir şekilde konum update'leri alabilmesini sağlar.

**İpucu**: Konum servisleri, izinler, UI elemanları ve diğer her şey için 3. parti kütüphaneler kullanılabilir.
