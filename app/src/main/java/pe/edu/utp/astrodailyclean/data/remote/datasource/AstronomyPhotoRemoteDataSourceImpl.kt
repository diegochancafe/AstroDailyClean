package pe.edu.utp.astrodailyclean.data.remote.datasource

import pe.edu.utp.astrodailyclean.BuildConfig
import pe.edu.utp.astrodailyclean.data.remote.dto.AstronomyPhotoResponse
import pe.edu.utp.astrodailyclean.data.remote.service.IRetrofitApi
import javax.inject.Inject

class AstronomyPhotoRemoteDataSourceImpl @Inject constructor(
    private val api: IRetrofitApi
) : AstronomyPhotoRemoteDataSource {

    override suspend fun fetchPhotos(startDate: String, endDate: String): List<AstronomyPhotoResponse> {
        return try {
            val response = api.getPhoto(BuildConfig.NASA_API_KEY, startDate, endDate)
            if (response.isSuccessful) {
                response.body() ?: emptyList()  // if response is successful then return body
            } else {
                emptyList() // if error is response then return empty list
            }
        } catch (e: Exception) {
            emptyList() // if exception is thrown then return empty list
        }
//
//        return listOf(
//            AstronomyPhotoResponse(
//                copyright = "SADR Observatory Team",
//                date = "2026-03-30",
//                explanation = "What's happened to the center of this galaxy?  Dramatic dust lanes run across the center of unusual elliptical galaxy Centaurus A. These dust lanes are so thick they almost completely obscure the galaxy's center in visible light.  This is particularly unusual as Cen A's older stars and oval shape are characteristic of a giant elliptical galaxy, a galaxy type typically low in dark dust.  Pictured in this deep image is a complex network of foreground gas and dust, as well as shells of dim stars and a jet projecting to the upper right.  Also known as NGC 5128, Cen A is surely the result of a galactic collision where many young dust-creating stars were formed.  However, details of the creation of Cen A's unusually active center and iconic central dust lanes are still being researched.  Cen A lies only 13 million light years away, making it the closest active galaxy.    Jigsaw Galaxy: Astronomy Puzzle of the Day",
//                highDefinitionUrl = "https://apod.nasa.gov/apod/image/2603/CenA_SADR_5000.jpg",
//                mediaType = "image",
//                serviceVersion = "v1",
//                title = "Peculiar Elliptical Galaxy Centaurus A",
//                url = "https://apod.nasa.gov/apod/image/2603/CenA_SADR_1080.jpg"
//            ),
//            AstronomyPhotoResponse(
//                copyright = null,
//                date = "2026-03-31",
//                explanation = "Titania's tortured terrain is a mix of canyons, cliffs, and craters.  NASA's interplanetary robot spacecraft Voyager 2 passed the largest moon of Uranus in 1986 and took the feature picture. That the trenches of Titania resemble those on another moon of Uranus, Ariel, indicate that Titania underwent some violent surface event possibly related to water freezing and expanding in its distant past.  Although Titania is Uranus's largest moon, it is only about half the radius of Triton - the largest moon of Uranus's sister planet Neptune, which itself is slightly smaller than Earth's Moon.  Titania, discovered by William Herschel in 1787, is essentially a large dirty iceball that is composed of about half water-ice and half rock.  There is recent speculation that radioactive heating melts some underground ice into oceans.",
//                highDefinitionUrl = "https://apod.nasa.gov/apod/image/2603/Titania_Voyager2_960.jpg",
//                mediaType = "image",
//                serviceVersion = "v1",
//                title = "Uranus's Largest Moon: Titania",
//                url = "https://apod.nasa.gov/apod/image/2603/Titania_Voyager2_960.jpg"
//            ),
//            AstronomyPhotoResponse(
//                copyright = "NASA",
//                date = "2026-04-01",
//                explanation ="What unexpected things do you see when you look up at the night sky? Today’s image resembles an abstract painting, with large swaths of color strewn across a cosmic canvas seemingly without design. Despite the image's abstract nature, the human mind finds patterns, identifying a large claw reaching up towards a floating bubble. Embedded within these seemingly random structures are the physical laws that govern how light and matter interact. The Claw (Sh2-157) and Bubble (NGC 7635) Nebulae glow colors that are mapped to the yellow and blue shown, indicating the presence of hydrogen and oxygen ionized by the intense light emitted from stars several times the mass of the Sun. This image depicts both the chaos and structure of astronomical processes, showing that a common thread between art and science is to look for the unexpected.",
//                highDefinitionUrl = "https://apod.nasa.gov/apod/image/2604/claw_bubble.jpg",
//                mediaType = "image",
//                serviceVersion = "v1",
//                title = "The Claw and Bubble Nebulae",
//                url = "https://apod.nasa.gov/apod/image/2604/claw_bubble_800.jpg"
//            )
//        )
    }
}