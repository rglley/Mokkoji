import { localAxios } from '@/util/http-commons'
const local = localAxios()

const url = '/results'

async function eventList(success, fail) {
  await local.get(`${url}/lists`).then(success).catch(fail)
}
export { eventList }
