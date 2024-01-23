/** @type {import('tailwindcss').Config} */

export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    container: {
      center: true,
    },

    extend: {
      colors: {
        'primary': '#C8B6FF',
        'primary2': '#E7C6FF',
        'primary3': '#AB90FF',
        'primary0': '#F9F6FF',
        'white': '#FFFFFF',
        'black': '#000000',
        'gray': '#EFEFEF',
      }
    }

  },
  plugins: [],

}
