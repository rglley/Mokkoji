const getImages = (number) => {
  let photos = []
  for (var i = 1; i <= number; i++) {
    photos.push(`src/assets/imgmosaic/${i}.png`)
  }
  return photos
}
export default getImages
