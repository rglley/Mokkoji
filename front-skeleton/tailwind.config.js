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
    
    colors: {
      // primary : 보라색, 진한 순서대로
      'primary': '#C8B6FF',
      'primary2': '#E7C6FF',
      'primary3': '#AB90FF',
      'white' : '#FFFFFF',
      'black' : '#000000',
      'gray' : '#EFEFEF',
    }
  },
  plugins: [],

}