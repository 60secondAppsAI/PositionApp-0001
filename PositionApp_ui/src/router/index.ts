import { createRouter, createWebHashHistory } from 'vue-router';
import { useAuth } from '../stores/auth';
import SignIn from '../components/SignIn.vue';
import Settings from '../components/Settings.vue';
import MyDashboard from '../components/MyDashboard.vue';

// Dynamic imports for module components
import Positions from '../components/Positions.vue';
import PositionDetail from '../components/PositionDetail.vue';
import PositionLots from '../components/PositionLots.vue';
import PositionLotDetail from '../components/PositionLotDetail.vue';
import PositionSnapshots from '../components/PositionSnapshots.vue';
import PositionSnapshotDetail from '../components/PositionSnapshotDetail.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/dashboard'
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: MyDashboard
  },
  
  // Dynamic module routes
  {
    path: '/positions',
    name: 'Positions',
    component: Positions
  },
  {
    path: '/position/:id',
    name: 'PositionDetail',
    component: PositionDetail,
    props: true
  },
  {
    path: '/positionLots',
    name: 'PositionLots',
    component: PositionLots
  },
  {
    path: '/positionLot/:id',
    name: 'PositionLotDetail',
    component: PositionLotDetail,
    props: true
  },
  {
    path: '/positionSnapshots',
    name: 'PositionSnapshots',
    component: PositionSnapshots
  },
  {
    path: '/positionSnapshot/:id',
    name: 'PositionSnapshotDetail',
    component: PositionSnapshotDetail,
    props: true
  },

  // Core application routes
  {
    path: '/signin',
    name: 'SignIn',
    component: SignIn
  },
//  {
//    path: '/users',
//    name: 'Users',
//    component: Users,
//    meta: { requiresAuth: true }
//  },
  {
    path: '/settings',
    name: 'Settings',
    component: Settings,
    meta: { requiresAuth: true }
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/dashboard'
  }
];

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes,
  scrollBehavior(to, from, savedPosition) {
    return savedPosition || { top: 0 };
  }
});

// Navigation guard for authentication
router.beforeEach((to, from, next) => {
  const auth = useAuth();
  
  if (to.meta.requiresAuth && !auth.isAuthenticated) {
    next({ name: 'SignIn', query: { redirect: to.fullPath } });
  } else if (to.name === 'SignIn' && auth.isAuthenticated) {
    next({ name: 'Dashboard' });
  } else {
    next();
  }
});

export default router;
